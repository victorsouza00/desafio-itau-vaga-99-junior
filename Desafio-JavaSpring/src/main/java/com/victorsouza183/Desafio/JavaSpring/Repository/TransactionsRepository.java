package com.victorsouza183.Desafio.JavaSpring.Repository;


import com.victorsouza183.Desafio.JavaSpring.Exceptions.UnprocessableTransactions;
import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class TransactionsRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TransactionsRepository.class);
    private final List<Transaction> transactions= new ArrayList<>();
    public final Logger logger = Logger.getLogger(TransactionsRepository.class.getName());



    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction){
        logger.info("Transação iniciada");

        if(transaction.getDataHora().isAfter(OffsetDateTime.now())){
            logger.severe("Data e Hora invalidas");
            throw new UnprocessableTransactions("Data e hora invalidas");
        }
        if(transaction.getValue().compareTo(BigDecimal.ZERO) <= 0){
            logger.severe("Valor invalido");
            throw new UnprocessableTransactions("Valor invalido");
        }

        transactions.add(transaction);
    }

}
