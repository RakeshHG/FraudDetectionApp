package com.finance.frauddetection.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {
    @GetMapping
    public String getAll(){
        return "Get All Fraud Alerts";
    }

//    @GetMapping("/open")
//    public String getOpenAlerts(){
//        return "Get All Open Alerts";
//    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable int id){
        return "Update Status of ID : " + id;
    }
}
