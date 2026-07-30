package com.finance.frauddetection.Repository;

import com.finance.frauddetection.Model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> findAll();

    Transaction findById(int id);

    int save(Transaction transaction);


    //Updating Status, Risk Score, Reasons
    void updateStatus(int id, String status);
}
