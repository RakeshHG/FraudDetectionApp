package com.finance.frauddetection.Repository;

import com.finance.frauddetection.Model.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Profile("Memory")
public class InMemoryTransactionRepository implements ITransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public List<Transaction> findAll(){
        return transactions;
    }

    @Override
    public Transaction findById(int id){
        for(Transaction transaction : transactions){
            if(transaction.getId() == id){
                return transaction;
            }
        }
        return null;
    }

    @Override
    public int save(Transaction transaction){
        int newId = idCounter.getAndIncrement();
        transaction.setId(newId);
        transactions.add(transaction); //Ideally, could be insert query to the DB
        return newId; //This is the new transaction ID created.
    }

    //Updating Status, Risk Score, Reasons
    @Override
    public void updateStatus(int id, String status) {
        Transaction transaction = findById(id);
        if (transaction != null) {
            transaction.setStatus(status);
            //transaction.setRiskScore(riskScore);
            //transaction.setReasons(reasons);
        }
    }
}
