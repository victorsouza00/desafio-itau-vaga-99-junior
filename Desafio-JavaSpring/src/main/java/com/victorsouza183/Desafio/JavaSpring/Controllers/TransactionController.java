package com.victorsouza183.Desafio.JavaSpring.Controllers;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Services.TransactionsService;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
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

    @GetMapping("/transacoes")
    public List<Transaction> getEstatistics(
            @RequestParam Integer interval
    ){
        return transactionsService.allTransationsInTheLastSeconds(interval);
    }



}
