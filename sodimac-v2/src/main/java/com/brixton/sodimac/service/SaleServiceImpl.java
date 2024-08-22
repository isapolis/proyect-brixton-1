package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.request.BillRequestDTO;
import com.brixton.sodimac.dto.request.TicketRequestDTO;
import com.brixton.sodimac.dto.response.BillResponseDTO;
import com.brixton.sodimac.dto.response.TicketResponseDTO;
import com.brixton.sodimac.dto.sale.ProformaDTO;
import com.brixton.sodimac.model.client.LegalPerson;
import com.brixton.sodimac.model.client.NaturalPerson;
import com.brixton.sodimac.model.constants.Finances;
import com.brixton.sodimac.model.constants.Message;
import com.brixton.sodimac.model.sale.confirmed.Bill;
import com.brixton.sodimac.model.sale.confirmed.Ticket;
import com.brixton.sodimac.model.sale.proforma.Proforma;
import com.brixton.sodimac.model.sale.proforma.SaleDetail;
import com.brixton.sodimac.model.sale.proforma.StatusDetail;
import com.brixton.sodimac.model.sale.proforma.StatusProforma;
import com.brixton.sodimac.model.audit.TypeStatusForAudit;
import com.brixton.sodimac.model.employee.Employee;
import com.brixton.sodimac.model.mapper.CustomDateDeserializer;
import com.brixton.sodimac.model.product.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@ToString
@Slf4j
public class SaleServiceImpl implements SaleService {

    /*
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductService productService;


    private static final String USER_APP = "ADMIN";
    Map<Long, Proforma> temporalProformas = new HashMap<>();
    Map<Long, Proforma> confirmedProformas = new HashMap<>();
    Map<Long, Bill> saleBills = new HashMap<>();
    Map<Long, Ticket>saleTickets = new HashMap<>();


    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    public SaleServiceImpl(){
        module.addDeserializer(LocalDate.class, new CustomDateDeserializer());
        module.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });


        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(module);
        objectMapper.findAndRegisterModules();
    }

    @Override
    public Object createProforma(ProformaDTO inputProforma) {

        try{
            String jsonInput = objectMapper.writeValueAsString(inputProforma);
            Proforma proforma = objectMapper.readValue(jsonInput, Proforma.class);
            Employee employee =employeeService.getEmployeeSale(proforma.getEmployeeId());
            if(employee == null){
                return Message.EMPLOYEE_OUT_OF_AREA_OR_NOT_EXISTS;
            }
            //Inicia el detalle de proforma
            List<SaleDetail> details = new ArrayList<>();
            double sumaTotal = 0;
            for (SaleDetail detail : proforma.getDetails()) {
                Product product = productService.getProductBusiness(detail.getProductId());
                if (product == null) {
                    product = new Product();
                    //return Message.PRODUCT_NO_EXISTS_OR_ID_INVALID;
                }

                double availableQuantity = product.getQuantity() - getConfirmedQuantityForProduct(product.getId()) - detail.getQuantity();

                SaleDetail proformaDetail = new SaleDetail();
                proformaDetail.setProductId(product.getId());
                proformaDetail.setQuantity(detail.getQuantity());
                proformaDetail.setSalePrice(product.getPriceSale());
                proformaDetail.setTotal(product.getPriceSale() * detail.getQuantity());
                if(availableQuantity >= 0){
                    proformaDetail.setStatus(StatusDetail.AVAILABLE);
                    sumaTotal += proformaDetail.getTotal();
                }else{
                    proformaDetail.setStatus(StatusDetail.OUT_OF_STOCK);
                }

                details.add(proformaDetail);

            }

            proforma.setId(UUID.randomUUID().getMostSignificantBits());
            proforma.setCreatedAt(LocalDateTime.now());
            proforma.setStatus(TypeStatusForAudit.ACTIVE);
            proforma.setEmployeeId(employee.getId());
            proforma.setCreatedBy(USER_APP);
            proforma.setTotal(sumaTotal);
            proforma.setDetails(details);

            if(proforma.getStatusProforma().equals(StatusProforma.CONFIRMED)){
                confirmedProformas.put(proforma.getId(), proforma);
            }else{
                temporalProformas.put(proforma.getId(), proforma);
                proforma.setExpirationDate(proforma.getCreatedAt().plusDays(2));
            }
            String jsonOutput = objectMapper.writeValueAsString(proforma);
            ProformaDTO output = objectMapper.readValue(jsonOutput, ProformaDTO.class);
            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private double getConfirmedQuantityForProduct(long productId){
        double confirmedQuantity = 0;
        for(Proforma proforma : confirmedProformas.values()){
            for(SaleDetail detail : proforma.getDetails()){
                if(detail.getProductId() == productId){
                    confirmedQuantity += detail.getQuantity();
                }
            }
        }
        return confirmedQuantity;
    }

    public void checkAndExpireProformas() {
        List<Long> idParaEliminar = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Proforma proforma : temporalProformas.values()) {
            //si su fecha de expiración es anterior a la fecha y hora actuales
            if (proforma.getStatusProforma() == StatusProforma.TEMPORAL && proforma.getExpirationDate().isBefore(now)) {
                idParaEliminar.add(proforma.getId());
            }
        }
        for(Long id : idParaEliminar ){
            temporalProformas.remove(id);
        }
    }

    @Override
    public Object getProforma(long id) {

        Proforma proforma = confirmedProformas.get(id);
        if(proforma == null){
            proforma = temporalProformas.get(id);
        }
        if(proforma != null){
            try {
                String jsonOutput = objectMapper.writeValueAsString(proforma);
                ProformaDTO output = objectMapper.readValue(jsonOutput, ProformaDTO.class);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Message.PROFORMA_NOT_FOUND;
    }

    @Override
    public Object confirmSaleTicket(TicketRequestDTO confirmedTicket) {

        try{
            Proforma existingProforma = confirmedProformas.get(confirmedTicket.getProformaId());
            if(existingProforma == null){
                return "new Proforma()Proforma no existe o no esta confirmada";
            }

            List<SaleDetail> detailSales = new ArrayList<>();
            double sumaTotal = 0;
            for(SaleDetail detail: existingProforma.getDetails()){
                if(detail.getStatus() == StatusDetail.AVAILABLE){
                    detailSales.add(detail);
                    sumaTotal += detail.getTotal();
                }
            }

            NaturalPerson naturalClient = clientService.getClientByDocument(confirmedTicket.getClient().getNumber());
            if (naturalClient == null) {
                String jsonInput = objectMapper.writeValueAsString(confirmedTicket.getClient());
                naturalClient = objectMapper.readValue(jsonInput, NaturalPerson.class);
                // Crear un nuevo cliente
                naturalClient = clientService.createClientNatural(naturalClient);
            }

            Ticket ticket = new Ticket();
            ticket.setIdTicket(UUID.randomUUID().getMostSignificantBits());
            ticket.setClient(naturalClient);
            ticket.setDetailSales(detailSales);
            ticket.setTotal(sumaTotal);
            ticket.setCreatedAt(LocalDateTime.now());
            ticket.setCreatedBy(USER_APP);
            ticket.setStatus(TypeStatusForAudit.ACTIVE);

            for(SaleDetail detail: detailSales){
                Product producto = productService.getProductBusiness(detail.getProductId());
                producto.setQuantity(producto.getQuantity() - detail.getQuantity());
                productService.updateProduct(producto.getId(), producto);
            }

            String jsonOutputTicket = objectMapper.writeValueAsString(ticket);
            TicketResponseDTO outputTicket = objectMapper.readValue(jsonOutputTicket, TicketResponseDTO.class);
            saleTickets.put(ticket.getIdTicket(), ticket);

            confirmedProformas.remove(existingProforma.getId());

            return outputTicket;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object confirmSaleBill(BillRequestDTO confirmedBill) {

        try {

            // 1. Validar si la proforma existe
            Proforma existingProforma = confirmedProformas.get(confirmedBill.getProformaId());
            if (existingProforma == null) {
                return "new Proforma()Proforma no existe o ID incorrecto";
            }

            // Recorrer los detalles de la proforma y asignarlos al bill
            List<SaleDetail> detailSales = new ArrayList<>();
            for (SaleDetail detail: existingProforma.getDetails()){
                Product product = productService.getProductBusiness(detail.getProductId());
                if(product != null && product.getQuantity() >= detail.getQuantity()){
                    detailSales.add(detail);
                }
            }

            // 2. Validar y registrar el cliente (o recuperar si ya existe)
            LegalPerson legalClient = clientService.getClientByRuc(confirmedBill.getClient().getRuc());
            if (legalClient == null) {
                String jsonInput = objectMapper.writeValueAsString(confirmedBill.getClient());
                legalClient = objectMapper.readValue(jsonInput, LegalPerson.class);

                // Crear un nuevo cliente
                legalClient = clientService.createClientLegal(legalClient);
            }

            // 3. Registrar el comprobante de venta

            Bill bill = new Bill();
            bill.setIdBill(UUID.randomUUID().getMostSignificantBits());
            bill.setClient(legalClient); // Actualizar con el cliente correcto
            bill.setDetailSales(detailSales); // Agregar detalles de ventas desde la proforma
            double subTotal = detailSales.stream().mapToDouble(SaleDetail::getTotal).sum();
            bill.setSubTotal(subTotal);
            bill.setTotal(subTotal * (1 + Finances.IGV));
            bill.setIgv(subTotal*Finances.IGV);
            bill.setCreatedAt(LocalDateTime.now());
            bill.setCreatedBy(USER_APP);
            bill.setStatus(TypeStatusForAudit.ACTIVE);

            // 4. Actualizar el stock de productos
            for(SaleDetail detail: detailSales) {
                Product producto = productService.getProductBusiness(detail.getProductId());

                producto.setQuantity(producto.getQuantity() - detail.getQuantity());

                productService.updateProduct(producto.getId(), producto);

            }

            String jsonOutputBill = objectMapper.writeValueAsString(bill);
            BillResponseDTO outputBill = objectMapper.readValue(jsonOutputBill, BillResponseDTO.class);

            saleBills.put(bill.getIdBill(), bill);
            //Eliminar la proforma confirmada
            confirmedProformas.remove(existingProforma.getId());


            return outputBill;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object getTicket(long id) {
        Ticket ticket = saleTickets.get(id);
        if (ticket != null){
            try {
                String jsonOutput = objectMapper.writeValueAsString(ticket);
                TicketResponseDTO output = objectMapper.readValue(jsonOutput, TicketResponseDTO.class);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getBill(long id) {
        Bill bill = saleBills.get(id);
        if(bill != null){
            try {
                String jsonOutput = objectMapper.writeValueAsString(bill);
                BillResponseDTO output = objectMapper.readValue(jsonOutput, BillResponseDTO.class);
                return output;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Object updateProforma(long id, ProformaDTO proformaDTO) {
        try {
            String jsonInput = objectMapper.writeValueAsString(proformaDTO);
            Proforma proformaTemporal = objectMapper.readValue(jsonInput, Proforma.class);


            Proforma original = temporalProformas.get(id);
            if (original == null) {
                return "new Proforma()Proforma no existe o ID incorrecto";
            }
            // Recorrer los detalles de la proforma y asignarlos al bill
            List<SaleDetail> detailSales = new ArrayList<>();
            detailSales.addAll(original.getDetails());
            if(original != null){
                original.setUpdatedAt(LocalDateTime.now());
                original.setUpdatedBy(USER_APP);
                original.setStatus(TypeStatusForAudit.ACTIVE);
                original.setEmployeeId(proformaTemporal.getEmployeeId());
                original.setDetails(detailSales);
                original.setStatusProforma(StatusProforma.CONFIRMED);

                String jsonOutput = objectMapper.writeValueAsString(original);
                ProformaDTO output = objectMapper.readValue(jsonOutput, ProformaDTO.class);

                confirmedProformas.put(original.getId(), original);
                return output;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
*/

}
