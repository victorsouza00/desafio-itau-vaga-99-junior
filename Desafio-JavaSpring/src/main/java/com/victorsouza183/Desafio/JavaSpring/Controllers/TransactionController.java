package com.victorsouza183.Desafio.JavaSpring.Controllers;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Services.TransactionsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private TransactionsService transactionsService;

    public TransactionController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/transacao")
    public void addTransactions(
            @RequestBody Transaction transaction
    ){
        transactionsService.addTransaction(transaction);
    }



}
