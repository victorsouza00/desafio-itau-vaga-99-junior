package com.victorsouza183.Desafio.JavaSpring.Controllers;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Models.TransactionsStatistcs;
import com.victorsouza183.Desafio.JavaSpring.Services.StatistcsTransactionsService;
import com.victorsouza183.Desafio.JavaSpring.Services.TransactionsService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionsService transactionsService;
    private final StatistcsTransactionsService statistcsTransactionsService;
    public TransactionController(TransactionsService transactionsService,StatistcsTransactionsService statistcsTransactionsService) {
        this.transactionsService = transactionsService;
        this.statistcsTransactionsService = statistcsTransactionsService;

    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> addTransactions(
            @RequestBody Transaction transaction
    ){
        transactionsService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/transacoes")
    public List<Transaction> getTransactionsInTheLastSeconds(
            @RequestParam Integer interval
    ){
        return transactionsService.allTransationsInTheLastSeconds(interval);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> removeTransactions(){
        transactionsService.removeAllTransactions();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<TransactionsStatistcs> getTransactionsStatistcs(
            @RequestParam(required = false,defaultValue = "60")
            Integer interval
    ){
        return ResponseEntity.ok(statistcsTransactionsService.getStatistcs(interval));
    }


}
