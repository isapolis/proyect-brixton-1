package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.compras.IncomeDTO;
import com.brixton.sodimac.model.compras.Buy;
import com.brixton.sodimac.model.compras.OrderBuy;
import com.brixton.sodimac.model.compras.ProductToBuy;
import com.brixton.sodimac.model.constants.Message;
import com.brixton.sodimac.model.employee.Employee;
import com.brixton.sodimac.model.logistic.Income;
import com.brixton.sodimac.model.logistic.IncomeStatus;
import com.brixton.sodimac.model.mapper.CustomDateDeserializer;
import com.brixton.sodimac.model.product.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LogisticServiceImpl implements LogisticService{

    /*
    @Autowired
    private BuyService buyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;
    Map<Long, Income> incomeCanceleds = new HashMap<>();
    Map<Long, Income> incomeReceiveds = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    public LogisticServiceImpl() {
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
    public Object registerIncome(IncomeDTO input) {
        try {
            String jsonInput = objectMapper.writeValueAsString(input);
            Income incomeTemp = objectMapper.readValue(jsonInput, Income.class);
            incomeTemp.setId(UUID.randomUUID().getMostSignificantBits());
            incomeTemp.setIncomeStatus(IncomeStatus.RECEIVED);
            Employee employeeLogistic = employeeService.getEmployeeLogistic(incomeTemp.getIdEmployee());
            if (employeeLogistic==null){
                return Message.EMPLOYEE_OUT_OF_AREA_OR_NOT_EXISTS;
            }
            incomeTemp.setIdEmployee(employeeLogistic.getId());
            OrderBuy orderBuy = buyService.getOrderBuyRequested(incomeTemp.getIdOrderBuy());
            Buy buy = buyService.getBuyApproved(orderBuy.getIdBuy());

            for (ProductToBuy temp: buy.getProductToBuys()){
                Product product = productService.getProductForBuyBusiness(temp.getIdProduct());
                if (product==null){
                    return "No existen productos por comprar";
                }
                if (!incomeTemp.getIncomeStatus().equals(IncomeStatus.RECEIVED)){
                    return "No existe un ingreso de Productos registrados";
                }
                productService.buyProduct(product.getId(), temp.getRequiredQuantity());
            }
            incomeReceiveds.put(incomeTemp.getId(),incomeTemp);
            String jsonOutput = objectMapper.writeValueAsString(incomeTemp);
            IncomeDTO output = objectMapper.readValue(jsonOutput, IncomeDTO.class);

            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Object canceledIncome(long idIncome, IncomeDTO incomeToUpdate) {
        try {
            String jsonInput = objectMapper.writeValueAsString(incomeToUpdate);
            Income incomeTemp = objectMapper.readValue(jsonInput, Income.class);
            Income original = incomeReceiveds.get(idIncome);
            if (original==null){
                return Message.INCOME_NO_REGISTERED;
            }
            incomeTemp.setIncomeStatus(IncomeStatus.CANCELED);
            incomeCanceleds.put(incomeTemp.getId(), incomeTemp);
            incomeReceiveds.remove(incomeTemp.getId());
            String jsonOutput = objectMapper.writeValueAsString(incomeTemp);
            IncomeDTO output = objectMapper.readValue(jsonOutput, IncomeDTO.class);

            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
