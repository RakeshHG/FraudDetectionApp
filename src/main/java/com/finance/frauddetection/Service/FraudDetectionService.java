package com.finance.frauddetection.Service;

import com.finance.frauddetection.Model.Customer;
import com.finance.frauddetection.Model.Transaction;
import com.finance.frauddetection.Repository.CustomerRepository;
import com.finance.frauddetection.Repository.ITransactionRepository;
//import com.finance.frauddetection.Repository.InMemoryTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {
    private ITransactionRepository transactionRepository;
    private CustomerRepository customerRepository;

    public FraudDetectionService(ITransactionRepository transactionRepository,
                                 CustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.findById(id);
    }

    public Transaction processTransaction(Transaction transaction){
        int newGeneratedIdTxn = transactionRepository.save(transaction);
        transaction.setId(newGeneratedIdTxn);

        //Fraud Detection Main Logic
        int riskScore = 0;
        List<String> reasons = new ArrayList<>();
        //Rule #1 - High Amount
        if(transaction.getAmount().compareTo(new BigDecimal(100000)) > 0){
            riskScore += 40;
            reasons.add("High Transaction Amount Rs. (" + transaction.getAmount() + ")");
        }

        //Rule #2 - Odd Hours
        int hour = transaction.getTxnTimestamp().getHour();
        if(hour>=0 && hour<5){
            reasons.add ("Transaction made during odd hours (" + hour +":00)");
            riskScore += 20;
        }

        //Rule #3 - Location Mismatch
        Customer customer = customerRepository.getCustomerById(transaction.getCustomerId());
        if(customer != null && !customer.getRegisteredCountry().
                equalsIgnoreCase(transaction.getTxnCountry())){
            reasons.add("Customer country mismatched - " + transaction.getTxnCountry());
            riskScore += 30;
        }

        if(!reasons.isEmpty()){
            //Updating transaction status as Flagged
            transaction.setStatus("FLAGGED");
            transactionRepository.updateStatus(transaction.getId(), "FLAGGED");
        }else{
            //Updating transaction status as Success
            transaction.setStatus("SUCCESS");
            transactionRepository.updateStatus(transaction.getId(), "FLAGGED");
        }

        return transaction;
    }

    // FraudDetectionService.java
    public void updateStatus(int id, String status) {
        transactionRepository.updateStatus(id, status);
    }

}
