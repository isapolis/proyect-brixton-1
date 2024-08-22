package com.brixton.sodimac.controller;

import com.brixton.sodimac.dto.compras.BuyDTO;
import com.brixton.sodimac.dto.compras.ReasonRejectDTO;
import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/compras")
public class BuyController {

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
