package com.victorsouza183.Desafio.JavaSpring.Services;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TransactionsService {

    private TransactionsRepository transactionsRepository;
    private final Logger logger = Logger.getLogger(TransactionsService.class.getName());

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public void addTransaction(Transaction transaction){
        transactionsRepository.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions(){
       return transactionsRepository.getTransactions();
    }

    public List<Transaction> allTransationsInTheLastSeconds(Integer interval){
        logger.info("Buscando transações feitas a "+interval+" Segundos");

        OffsetDateTime pastInterval = OffsetDateTime.now().minusSeconds(interval);
        return getAllTransactions().stream()
                .filter(transction-> transction.getDataHora().isAfter(pastInterval))
                .collect(Collectors.toList());
    }

}
