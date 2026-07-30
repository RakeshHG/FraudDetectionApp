package com.finance.frauddetection.Controller;

import com.finance.frauddetection.Model.Transaction;
import com.finance.frauddetection.Service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TransactionController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private FraudDetectionService fraudDetectionService;
    public TransactionController(FraudDetectionService fraudDetectionService){
        this.fraudDetectionService = fraudDetectionService;
    }

    @GetMapping
    public List<Transaction> getAll(){
       return fraudDetectionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id){
        return fraudDetectionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return fraudDetectionService.processTransaction(transaction);
    }

    // Inside your TransactionController class:
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable int id, @RequestParam String status) {
        fraudDetectionService.updateStatus(id, status);

//        if (isUpdated) {
//            return ResponseEntity.ok("Transaction status updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
//        }
    }
}
