package com.brixton.sodimac.controller;

import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.dto.request.compras.ReqBuyRequestDTO;
import com.brixton.sodimac.dto.response.OrderBuyResponseDTO;
import com.brixton.sodimac.dto.response.compras.ReqBuyResponseDTO;
import com.brixton.sodimac.service.BuyService;
import jakarta.validation.Valid;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ToString
@Slf4j
@RequestMapping("/v1/management/compras")
public class BuyController {
    @Autowired
    private BuyService buyService;
    @PostMapping("/")
    public ResponseEntity<ReqBuyResponseDTO> createRequestBuy(@Valid @RequestBody ReqBuyRequestDTO reqBuyRequestDTO){
        ReqBuyResponseDTO reqBuy = buyService.createRequestBuy(reqBuyRequestDTO);
        return ResponseEntity.ok(reqBuy);
    }
    @GetMapping("/{idEmployee}/{buyStatus}")
    public ResponseEntity<List<ReqBuyResponseDTO>> checkStatusOfRequestBuys(@PathVariable long idEmployee ,@PathVariable String buyStatus){
        return new ResponseEntity<>(buyService.checkStatusOfRequestBuys(idEmployee, buyStatus), HttpStatus.OK);
    }
    @GetMapping("/confirmed")
    public ResponseEntity<List<ReqBuyResponseDTO>> getConfirmedBuys(){
        return new ResponseEntity<>(buyService.getConfirmedBuys(),HttpStatus.OK);
    }
    @DeleteMapping("/reject/{id}")
    public  ResponseEntity<?> rejectRequestBuy(@Valid @PathVariable long id, @RequestBody String reason){
        buyService.rejectRequestBuy(id,reason);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/orderbuy/{idemployee}")
    public ResponseEntity<OrderBuyResponseDTO> createOrderBuy(@Valid @PathVariable long idemployee, @RequestBody OrderBuyRequestDTO orderBuyRequestDTO){
        log.info("Hola estoy por esta ne");
        OrderBuyResponseDTO orderBuyResponseDTO = buyService.createOrderBuy(idemployee, orderBuyRequestDTO);
        return  ResponseEntity.ok(orderBuyResponseDTO);
    }

    /*
    @Autowired
    private BuyService buyService;
    @PostMapping("/")
    public ResponseEntity<Object> createRequerimentForBuy(@RequestBody BuyDTO requerimentBuy){
        Object response = buyService.createRequerimentForBuy(requerimentBuy);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            // Body nulo, devolver 400
            //return ResponseEntity.badRequest().build();
            //Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idBuy}")
    public ResponseEntity<Object> confirmRequerimentForBuy(@PathVariable long idBuy, @RequestBody BuyDTO requerimentBuy){
        Object response = buyService.confirmRequerimentForBuy(idBuy, requerimentBuy);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            // Body nulo, devolver 400
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idBuy}/{status}")
    public ResponseEntity<Object> checkStatusOfRequerimentForBuy(@PathVariable long idBuy, @PathVariable String status){
        Object buyTemp = buyService.checkStatusOfRequerimentForBuy(idBuy, status);
        if (buyTemp!=null){
            return ResponseEntity.ok(buyTemp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/temporary")
    public ResponseEntity<List<Object>> getTemporaryBuys(){
        List<Object> temporaryBuys = buyService.getTemporaryBuys();
        if (!temporaryBuys.isEmpty()) {
            return ResponseEntity.ok(temporaryBuys);
        } else {
            // Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/confirmed")
    public ResponseEntity<List<Object>> getConfirmedBuys(){
        List<Object> confirmedBuys = buyService.getConfirmedBuys();
        if (!confirmedBuys.isEmpty()) {
            return ResponseEntity.ok(confirmedBuys);
        } else {
            // Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Object>> getApprobedBuys(){
        List<Object> approvedBuys = buyService.getApprovedBuys();
        if (!approvedBuys.isEmpty()) {
            return ResponseEntity.ok(approvedBuys);
        } else {
            // Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idBuy}/reject")
    public ResponseEntity<Object> rejectBuy(@PathVariable long idBuy, @RequestBody ReasonRejectDTO reason){
        //return ResponseEntity.ok(buyService.rejectBuy(idBuy, reason));
        Object buyRejected = buyService.rejectBuy(idBuy, reason);
        if (buyRejected!=null){
            return ResponseEntity.accepted().build();
        } else {
            // Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/orderBuy/")
    public ResponseEntity<Object> createOrderBuy(@RequestBody OrderBuyRequestDTO orderBuy){
        Object response = buyService.createOrderBuy(orderBuy);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            // Body nulo, devolver 400
            //return ResponseEntity.badRequest().build();
            //Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orderBuy/{idOrderBuy}")
    public ResponseEntity<List<Object>> getOrderBuy(@PathVariable long idOrderBuy){
        List<Object> buyTemp = buyService.getOrderBuy(idOrderBuy);
        if (!buyTemp.isEmpty()) {
            return ResponseEntity.ok(buyTemp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
*/

}
