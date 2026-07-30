package com.finance.frauddetection.Model;

// Transaction.java
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    private int id;
    private int customerId;
    private BigDecimal amount;
    private String txnCountry;
    private LocalDateTime txnTimestamp;
    private String status;
//    private int riskScore;
//    private List<String> reasons;

    // Add this All-Args Constructor
    public Transaction(int id, int customerId, BigDecimal amount, String txnCountry, LocalDateTime txnTimestamp, String status) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.txnCountry = txnCountry;
        this.txnTimestamp = txnTimestamp;
        this.status = status;
    }

    // Add this No-Args Constructor (needed by Spring/Jackson)
    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTxnCountry() {
        return txnCountry;
    }

    public void setTxnCountry(String txnCountry) {
        this.txnCountry = txnCountry;
    }

    public LocalDateTime getTxnTimestamp() {
        return txnTimestamp;
    }

    public void setTxnTimestamp(LocalDateTime txnTimestamp) {
        this.txnTimestamp = txnTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public int getRiskScore() {
//        return riskScore;
//    }
//
//    public void setRiskScore(int riskScore) {
//        this.riskScore = riskScore;
//    }
//
//    public List<String> getReasons() {
//        return reasons;
//    }
//
//    public void setReasons(List<String> reasons) {
//        this.reasons = reasons;
//    }

}