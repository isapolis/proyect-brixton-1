package com.brixton.sodimac.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/proforma")
@Slf4j
public class SaleController {

    /*
    @Autowired
    private SaleService saleService;

    @PostMapping("/")
    public ResponseEntity<Object> createProforma(@RequestBody ProformaDTO proformaDTO) {
        Object proforma = saleService.createProforma(proformaDTO);
        if(proforma != null){
            return ResponseEntity.ok(proforma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProforma(@PathVariable long id) {
        Object proforma = saleService.getProforma(id);
        if (proforma != null) {
            return ResponseEntity.ok(proforma);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/confirmTicket")
    public ResponseEntity<Object> confirmSaleTicket(@RequestBody TicketRequestDTO confirmedTicket) {
        Object sale = saleService.confirmSaleTicket(confirmedTicket);
        if(sale != null){
            return ResponseEntity.ok(sale);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/confirmBill")
    public ResponseEntity<Object> confirmSaleBill(@RequestBody BillRequestDTO confirmedBill){
        Object bill = saleService.confirmSaleBill(confirmedBill);
        if(bill != null){
            return ResponseEntity.ok(bill);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<Object> getBill(long id){
        Object bill = saleService.getBill(id);
        if(bill != null){
            return ResponseEntity.ok(bill);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Object> getTicket(long id){
        Object ticket = saleService.getTicket(id);
        if(ticket != null){
            return ResponseEntity.ok(ticket);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProforma(@PathVariable long id, @RequestBody ProformaDTO proformaDTO){
        Object proformaUpdate = saleService.updateProforma(id,proformaDTO);
        if(proformaUpdate != null){
            return ResponseEntity.ok(proformaUpdate);
        }else{
            return  new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }
*/

}
