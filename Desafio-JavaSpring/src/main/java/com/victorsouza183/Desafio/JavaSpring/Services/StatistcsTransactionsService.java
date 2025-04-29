package com.victorsouza183.Desafio.JavaSpring.Services;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Models.TransactionsStatistcs;
import com.victorsouza183.Desafio.JavaSpring.Repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatistcsTransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionsService transactionsService;

    public StatistcsTransactionsService(TransactionsRepository transactionsRepository, TransactionsService transactionsService) {
        this.transactionsRepository = transactionsRepository;
        this.transactionsService = transactionsService;
    }

    public TransactionsStatistcs getStatistcs(Integer interval){
        List<Transaction> lastTransactions = transactionsService.allTransationsInTheLastSeconds(interval);

        if (lastTransactions.isEmpty()){
            return new TransactionsStatistcs(
                            0,
                            0,
                            0,
                            0,
                            0);
        }

        DoubleSummaryStatistics estatisticas = lastTransactions.stream()
                .mapToDouble(transaction -> transaction.getValue().doubleValue()).summaryStatistics();


        return  new TransactionsStatistcs(
                estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }


}
