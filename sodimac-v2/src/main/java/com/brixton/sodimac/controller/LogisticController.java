package com.brixton.sodimac.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/logistic")
public class LogisticController {

    /*
    @Autowired
    private LogisticService logisticService;
    @PostMapping("/")
    public ResponseEntity<Object> registerIncome(@RequestBody IncomeDTO input){
        Object response = logisticService.registerIncome(input);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            // Body nulo, devolver 400
            //return ResponseEntity.badRequest().build();
            //Body nulo, devolver 404
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idIncome}")
    public ResponseEntity<Object> canceledIncome(@PathVariable long idIncome, @RequestBody IncomeDTO incomeToUpdate) {
        Object incomeUpdate = logisticService.canceledIncome(idIncome, incomeToUpdate);
        if(incomeUpdate != null){
            return ResponseEntity.ok(incomeUpdate);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(404));

    }
*/

}
