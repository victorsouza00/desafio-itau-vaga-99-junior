package com.victorsouza183.Desafio.JavaSpring.Services;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    private TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public void addTransaction(Transaction transaction){
        transactionsRepository.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions(){
       return transactionsRepository.getTransactions();
    }
}
