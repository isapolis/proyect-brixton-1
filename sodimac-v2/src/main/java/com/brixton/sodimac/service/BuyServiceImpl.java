package com.brixton.sodimac.service;

import com.brixton.sodimac.controller.manageexception.GenericNotFoundException;
import com.brixton.sodimac.data.entity.compras.BuyStatus;
import com.brixton.sodimac.data.entity.compras.OrderBuy;
import com.brixton.sodimac.data.entity.compras.ProductToBuy;
import com.brixton.sodimac.data.entity.compras.RequestBuy;
import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.data.entity.logistic.StatusLogistic;
import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.data.enums.RegistryStateType;
import com.brixton.sodimac.data.repository.*;
import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.dto.request.compras.ReqBuyRequestDTO;
import com.brixton.sodimac.dto.response.OrderBuyResponseDTO;
import com.brixton.sodimac.dto.response.compras.ReqBuyResponseDTO;
import com.brixton.sodimac.service.mapper.OrderBuyMapper;
import com.brixton.sodimac.service.mapper.RequestBuyMapper;
import com.brixton.sodimac.service.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brixton.sodimac.service.utils.FuntionalBusinessInterfaces.auditCreation;
import static com.brixton.sodimac.service.utils.FuntionalBusinessInterfaces.auditUpdate;

@Service
@Slf4j
public class BuyServiceImpl implements BuyService {
    @Autowired
    private RequestBuyRepository requestBuyRepository;
    @Autowired
    private ProductToBuyRepository productToBuyRepository;
    @Autowired
    private BuyStatusRepository buyStatusRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatusLogisticRepository statusLogisticRepository;
    @Autowired
    private OrderBuyRepository orderBuyRepository;

    @Autowired// lo recomendable es trabajar con la implementeación de los metodos usados
    private ProductService productService;

    @Override
    public ReqBuyResponseDTO createRequestBuy(ReqBuyRequestDTO reqBuyRequestDTO){
        RequestBuy newRequestBuy = RequestBuyMapper.INSTANCE.reqBuyRequestDTOToRequestBuy(reqBuyRequestDTO);
        auditCreation.accept(newRequestBuy);
        BuyStatus buyStatus = buyStatusRepository.findById(newRequestBuy.getBuyStatus().getId()).orElseThrow(()-> new GenericNotFoundException("Id de Estado de compra no encontrado"));
        newRequestBuy.getBuyStatus().setName(buyStatus.getName());
        Employee authorizedEmployee = employeeService.findEmployeeByArea(newRequestBuy.getEmployeeRequester().getId(), areaRepository.findByName(Constants.EMPLOYEE_LOGISTIC));
        newRequestBuy.setEmployeeRequester(authorizedEmployee);

        if (newRequestBuy.getBuyStatus().getName().equals(Constants.BUY_STATUS_CONFIRMED)){
            newRequestBuy.setReasonStatus(Constants.BUY_STATUS_CONFIRMED);
            newRequestBuy.setEmployeeAssigned(employeeService.findRandomEmployeeByArea(Constants.EMPLOYEE_SHOPPING));
            requestBuyRepository.save(newRequestBuy);//en este momento se crea su id autogenerado
            for (ProductToBuy productToBuy: newRequestBuy.getProductToBuys()){
                Product product = productService.getProductFromData(productToBuy.getProduct().getId());
                productToBuy.getProduct().setName(product.getName());
                productToBuy.setRequestBuy(newRequestBuy);
                productToBuy.setQuantityStock(product.getQuantity());
                productToBuy.setQuantityInTransit(0);
                productToBuy.setExpectedQuantity(productToBuy.getQuantityStock()+productToBuy.getQuantityInTransit()+productToBuy.getRequiredQuantity());
                productToBuyRepository.save(productToBuy);
            }
        } else {
            newRequestBuy.setReasonStatus(buyStatus.getName());
        }
        for (ProductToBuy productToBuy: newRequestBuy.getProductToBuys()){
            Product product = productService.getProductFromData(productToBuy.getProduct().getId());
            productToBuy.getProduct().setName(product.getName());
            productToBuy.setRequestBuy(newRequestBuy);
            productToBuy.setQuantityStock(product.getQuantity());
            productToBuy.setQuantityInTransit(0);
            productToBuy.setExpectedQuantity(productToBuy.getQuantityStock()+productToBuy.getQuantityInTransit()+productToBuy.getRequiredQuantity());
        }
        return RequestBuyMapper.INSTANCE.requestBuyToRequestBuyResponseDTO(newRequestBuy);
    }

    @Override
    public List<ReqBuyResponseDTO> checkStatusOfRequestBuys(long idEmployee, String status) {
        List<ReqBuyResponseDTO> listRequestBuys = new ArrayList<>();
        Employee employeeLogistic = employeeService.findEmployeeByArea(idEmployee, areaRepository.findByName(Constants.EMPLOYEE_LOGISTIC));
        BuyStatus buyStatus = buyStatusRepository.findByName(status).orElseThrow(()-> new GenericNotFoundException("Estado no identificado"));
        List<RequestBuy> requestBuys = requestBuyRepository.findByBuyStatus(buyStatus);
        for (RequestBuy reqBuy: requestBuys){
            ReqBuyResponseDTO reqBuyTemp= RequestBuyMapper.INSTANCE.requestBuyToRequestBuyResponseDTO(reqBuy);
            listRequestBuys.add(reqBuyTemp);
        }
        return listRequestBuys;
    }
    @Override
    public List<ReqBuyResponseDTO> getConfirmedBuys(){
        List<ReqBuyResponseDTO> reqBuyConfirmeds =new ArrayList<>();
        BuyStatus buyStatus = buyStatusRepository.findByName(Constants.BUY_STATUS_CONFIRMED).orElseThrow(()-> new GenericNotFoundException("Estado no identificado"));
        List<RequestBuy> requestBuys = requestBuyRepository.findByBuyStatus(buyStatus);
        for (RequestBuy reqBuyTemp: requestBuys){
            ReqBuyResponseDTO reqBuyResponseDTO = RequestBuyMapper.INSTANCE.requestBuyToRequestBuyResponseDTO(reqBuyTemp);
            reqBuyConfirmeds.add(reqBuyResponseDTO);
        }
        return reqBuyConfirmeds;
    }
    @Override
    public void rejectRequestBuy(long id, String reason){
        RequestBuy requestBuy = requestBuyRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Solicitud de compra no registrada"));
        requestBuy.setRegistryState(RegistryStateType.INACTIVE);
        auditUpdate.accept(requestBuy);
        BuyStatus buyStatus = buyStatusRepository.findByName(Constants.BUY_STATUS_REJECTED).orElseThrow(()-> new GenericNotFoundException("Estado no identificado"));
        requestBuy.setBuyStatus(buyStatus);
        requestBuy.setReasonStatus(reason);
        requestBuyRepository.save(requestBuy);
    }
    @Override
    public OrderBuyResponseDTO createOrderBuy(long idEmployee, OrderBuyRequestDTO orderBuyRequestDTO){
        Employee employeeShopping = employeeService.findEmployeeByArea(idEmployee, areaRepository.findByName(Constants.EMPLOYEE_SHOPPING));
        OrderBuy orderBuy = OrderBuyMapper.INSTANCE.orderBuyRequestDTOToOrderBuy(orderBuyRequestDTO);
        log.info("nueva linea " + orderBuy.getRequestBuy().getId());
        RequestBuy requestBuy = requestBuyRepository.findById(orderBuy.getRequestBuy().getId()).orElseThrow(()->new GenericNotFoundException("Solicitud de compra no registrada"));
        BuyStatus buyStatus = buyStatusRepository.findByName(Constants.BUY_STATUS_APPROVED).orElseThrow(()-> new GenericNotFoundException("Estado de solicitud no identificado"));
        requestBuy.setBuyStatus(buyStatus);
        //Generar la cantidad en transito para ProductToBuy o productos a comprar en la solicitud de compra.
        Map<Long, Double> quantityInTransits = new HashMap<>();
        for (ProductToBuy productToBuy:requestBuy.getProductToBuys()){
            quantityInTransits.merge(productToBuy.getId(), productToBuy.getRequiredQuantity(), Double::sum);
        }
        requestBuy.setReasonStatus(Constants.BUY_STATUS_APPROVED);

        auditUpdate.accept(requestBuy);
        requestBuyRepository.save(requestBuy);

        StatusLogistic statusRequested = statusLogisticRepository.findByGroupStatus(Constants.DESCRIPTION_STATUS_REQUESTED,Constants.GROUP_STATUS_ORDER).orElseThrow(()-> new GenericNotFoundException("Registro status_logistic no encontrado"));
        orderBuy.setStatusOrder(statusRequested);
        orderBuy.setDateStatus(LocalDate.now());
        auditCreation.accept(orderBuy);
        orderBuy.setEmployee(requestBuy.getEmployeeAssigned());
        orderBuyRepository.save(orderBuy);





        return OrderBuyMapper.INSTANCE.orderBuyToOrderBuyResponseDTO(orderBuy);


    }

//    public List<OrderBuyResponseDTO>



/*
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;

    private static final String USER_APP = "BRIXTON";
    Map<Long, BuyTemporalResponseDTO> buyTemporaryDTOs =new HashMap<>();
    Map<Long, Buy> buyTemporarys = new HashMap<>();
    Map<Long, Buy> buyConfirmeds = new HashMap<>();
    Map<Long, Buy> buyApproveds =new HashMap<>();
    Map<Long, Buy> buyRejects =new HashMap<>();
    Map<Long, OrderBuy> orderBuyRequesteds = new HashMap<>();
    Map<Long, Double> quantityInTransits = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    public BuyServiceImpl(){
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
    public Object createRequerimentForBuy(BuyDTO requerimentBuy) {
        Employee employee = employeeService.getEmployeeLogistic(requerimentBuy.getEmployeeId());
        if (employee==null){
            return Message.EMPLOYEE_OUT_OF_AREA_OR_NOT_EXISTS;
        }
        try {
            String jsonInput = objectMapper.writeValueAsString(requerimentBuy);
            Buy buy = objectMapper.readValue(jsonInput, Buy.class);
            buy.setCreatedAt(LocalDateTime.now());
            buy.setCreatedBy(USER_APP);
            buy.setStatus(TypeStatusForAudit.ACTIVE);
            buy.setId(UUID.randomUUID().getLeastSignificantBits());
            buy.setDateStatus(LocalDate.now());
            buy.setMotivoStatus(Message.MOTIVO_STATUS_TEMPORARY);
            List<ProductToBuy> productToBuys=new ArrayList<>();
            for (ProductToBuy tempo: buy.getProductToBuys()){
                Product prodTemp = productService.getProductBusiness(tempo.getIdProduct());
                if (prodTemp==null){
                    return Message.PRODUCT_NO_EXISTS_OR_ID_INVALID;
                }
                ProductToBuy productToBuy = new ProductToBuy();
                productToBuy.setIdProduct(prodTemp.getId());
                productToBuy.setName(prodTemp.getName());
                productToBuy.setQuantityStock(prodTemp.getQuantity());
                if (quantityInTransits.get(tempo.getIdProduct())==null){
                    productToBuy.setQuantityInTransit(0);
                } else {
                    productToBuy.setQuantityInTransit(quantityInTransits.get(productToBuy.getIdProduct()));
                }
                productToBuy.setRequiredQuantity(tempo.getRequiredQuantity());
                productToBuy.setExpectedAmount(productToBuy.getQuantityStock() + productToBuy.getQuantityInTransit() + productToBuy.getRequiredQuantity());
                productToBuys.add(productToBuy);

            }
            buy.setProductToBuys(productToBuys);
            buyTemporarys.put(buy.getEmployeeId(), buy);
            BuyTemporalResponseDTO output = new BuyTemporalResponseDTO();
            String jsonOutput = objectMapper.writeValueAsString(buy);
            BuyDTO temporal = objectMapper.readValue(jsonOutput, BuyDTO.class);
            output.setCreatedBy(USER_APP);
            output.setStatus(temporal.getStatus());
            output.setDateStatus(temporal.getDateStatus());
            output.setMotivoStatus(temporal.getMotivoStatus());
            output.setProductToBuys(temporal.getProductToBuys());
            buyTemporaryDTOs.put(output.getEmployeeId(), output);
            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object confirmRequerimentForBuy(long idEmployee, BuyDTO requerimentBuy) {

        try {
            String jsonInput = objectMapper.writeValueAsString(requerimentBuy);
            Buy buy = objectMapper.readValue(jsonInput, Buy.class);
            Buy buyToConfirm = new Buy();
            for (Buy original:buyTemporarys.values()){
                if (!original.equals(buyTemporarys.get(idEmployee))){
                    return Message.REQUEST_NO_EXISTS_OR_STATUS_INCORRECT;
                }
                original.setUpdatedAt(LocalDateTime.now());
                original.setUpdatedBy(USER_APP);
                original.setDateStatus(LocalDate.now());
                original.setBuyStatus(buy.getBuyStatus());
                original.setMotivoStatus(Message.MOTIVO_STATUS_CONFIRMED);
                buyToConfirm = original;
            }
            Employee employee = employeeService.choseEmployeeBuyAleatorio();
            if (employee==null){
                return Message.EMPLOYEE_OUT_OF_AREA_OR_NOT_EXISTS;
            }
            buyToConfirm.setBuyEmployeeId(employee.getId());
            buyTemporarys.remove(buyToConfirm.getEmployeeId());
            buyConfirmeds.put(buyToConfirm.getId(), buyToConfirm);
            String jsonOutput = objectMapper.writeValueAsString(buyToConfirm);
            BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object checkStatusOfRequerimentForBuy(long idBuy, String status) {
        try {
            if (status.toUpperCase().equals(BuyStatus.APPROVED.toString())){
                for (Buy buy:buyApproveds.values()){
                    if (buy.equals(buyApproveds.get(idBuy))){
                        String jsonOutput = objectMapper.writeValueAsString(buy);
                        BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                        return output;
                    }
                }
            } else if (status.toUpperCase().equals(BuyStatus.CONFIRMED.toString())) {
                for (Buy buy:buyConfirmeds.values()){
                    if (buy.equals(buyConfirmeds.get(idBuy))){
                        String jsonOutput = objectMapper.writeValueAsString(buy);
                        BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                        return output;
                    }
                }
            } else if (status.toUpperCase().equals(BuyStatus.REJECTED.toString())) {
                for (Buy buy:buyRejects.values()){
                    if (buy.equals(buyRejects.get(idBuy))){
                        String jsonOutput = objectMapper.writeValueAsString(buy);
                        BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                        return output;
                    }
                }
            } else {
                return Message.REQUEST_NO_EXISTS_OR_STATUS_INCORRECT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Object> getTemporaryBuys() {
        List<Object> buyFounds = new ArrayList<>();
        for (BuyTemporalResponseDTO buy: buyTemporaryDTOs.values()){
            try {
                String jsonOutput = objectMapper.writeValueAsString(buy);
                BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                buyFounds.add(output);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return buyFounds;
    }
    @Override
    public List<Object> getConfirmedBuys() {
        List<Object> buyFounds = new ArrayList<>();
        for (Buy buy: buyConfirmeds.values()){
            try {
                String jsonOutput = objectMapper.writeValueAsString(buy);
                BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                buyFounds.add(output);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return buyFounds;
    }
    @Override
    public List<Object> getApprovedBuys() {
        List<Object> buyFounds = new ArrayList<>();
        for (Buy buy: buyApproveds.values()){
            try {
                String jsonOutput = objectMapper.writeValueAsString(buy);
                BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
                buyFounds.add(output);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return buyFounds;
    }

    @Override
    public Object rejectBuy(long idBuy, ReasonRejectDTO reason) {
        try {
            Buy buyTemp = buyConfirmeds.get(idBuy);
            if (buyTemp!=null){

                buyTemp.setBuyStatus(BuyStatus.REJECTED);
                buyTemp.setDateStatus(LocalDate.now());
                buyTemp.setMotivoStatus(reason.getMotivo());
                buyTemp.setUpdatedAt(LocalDateTime.now());
                buyTemp.setUpdatedBy(USER_APP);
                buyConfirmeds.remove(buyTemp.getId());
                buyRejects.put(buyTemp.getId(), buyTemp);
                return buyTemp;
            } else {
                return Message.REQUEST_NO_EXISTS_OR_STATUS_INCORRECT;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object createOrderBuy(OrderBuyRequestDTO orderBuy) {
        Buy buyTemp = buyConfirmeds.get(orderBuy.getIdBuy());
        Employee employee = employeeService.getEmployeeBuy(orderBuy.getIdEmployee());
        LegalPerson legalClient = clientService.findClientLegal(orderBuy.getIdSupplier());
        if(buyTemp==null || employee==null){
            return  Message.REQUEST_NO_EXISTS_OR_STATUS_INCORRECT + " o " + Message.EMPLOYEE_OUT_OF_AREA_OR_NOT_EXISTS;
        }
        if (legalClient==null || !legalClient.isSupplier()){
            return Message.CLIENT_NO_EXISTS_OR_NOT_SUPPLIER;
        }
        if (buyTemp.getBuyStatus()!=BuyStatus.CONFIRMED){
            return Message.REQUEST_NO_EXISTS_OR_STATUS_INCORRECT;
        }
        try {
            String jsonInput = objectMapper.writeValueAsString(orderBuy);
            OrderBuy orderTemp = objectMapper.readValue(jsonInput, OrderBuy.class);

            buyTemp.setBuyStatus(BuyStatus.APPROVED);
            buyTemp.setMotivoStatus(Message.MOTIVO_STATUS_APPROVED);
            buyTemp.setDateStatus(LocalDate.now());
            buyTemp.setUpdatedAt(LocalDateTime.now());
            buyTemp.setUpdatedBy(USER_APP);
            buyApproveds.put(buyTemp.getId(),buyTemp);
            buyConfirmeds.remove(buyTemp.getId());
            orderTemp.setId(UUID.randomUUID().getMostSignificantBits());
            orderTemp.setCreatedAt(LocalDateTime.now());
            orderTemp.setCreatedBy(USER_APP);
            orderTemp.setDateStatus(LocalDateTime.now());
            orderTemp.setOrderStatus(StatusOrder.REQUESTED);
            orderBuyRequesteds.put(orderTemp.getId(), orderTemp);

            //Generar la cantidad en transito para ProductToBuy o productos a comprar en la solicitud de compra.
            QuantityInTransit quantityInTransit= new QuantityInTransit();
            for (ProductToBuy productRequired:buyApproveds.get(orderTemp.getIdBuy()).getProductToBuys()){
                if (quantityInTransits.containsKey(quantityInTransit.getIdProduct())){
                    double suma = quantityInTransits.get(quantityInTransit.getIdProduct()) + productRequired.getRequiredQuantity();
                    quantityInTransits.put(quantityInTransit.getIdProduct(), suma);
                } else {
                    quantityInTransit.setIdProduct(productRequired.getIdProduct());
                    quantityInTransit.setQuantity(productRequired.getRequiredQuantity());
                    quantityInTransits.put(quantityInTransit.getIdProduct(), quantityInTransit.getQuantity());
                }
            }

            String jsonOutput = objectMapper.writeValueAsString(orderTemp);
            OrderBuyResponseDTO output = objectMapper.readValue(jsonOutput, OrderBuyResponseDTO.class);
            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> getOrderBuy(long idOrderBuy){
        OrderBuy orderBuy = orderBuyRequesteds.get(idOrderBuy);
        Buy buy = buyApproveds.get(orderBuy.getIdBuy());
        List<Object> buyFounds = new ArrayList<>();
        try {
            String jsonOutput = objectMapper.writeValueAsString(buy);
            BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
            buyFounds.add(output);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return buyFounds;


//        List<BuyDTO> buyTemps = new ArrayList<>();
//        if (orderBuy==null){
//            return buyTemps;
//        }
//        for (OrderBuy order : orderBuyRequesteds.values()){
//            if (order!=null && idOrderBuy==order.getIdBuy()){
//                try {
//                    Buy temp =buyApproveds.get(order.getIdBuy());
//                    String jsonOutput = objectMapper.writeValueAsString(temp);
//                    BuyDTO output = objectMapper.readValue(jsonOutput, BuyDTO.class);
//                    buyTemps.add(output);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return buyTemps;
    }

    @Override
    public OrderBuy getOrderBuyRequested(long idOrderBuy) {
        OrderBuy orderTemp = orderBuyRequesteds.get(idOrderBuy);
        if (orderTemp!=null){
            return orderTemp;
        }
        return null;
    }

    @Override
    public Buy getBuyApproved(long idBuy) {
        Buy buyTemp = buyApproveds.get(idBuy);
        if (buyTemp!=null){
            return buyTemp;
        }
        return null;
    }
*/
}
