package com.victorsouza183.Desafio.JavaSpring.Services;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Models.TransactionsStatistcs;
import com.victorsouza183.Desafio.JavaSpring.Repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StatistcsTransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionsService transactionsService;
    public final Logger logger = Logger.getLogger(StatistcsTransactionsService.class.getName());

    public StatistcsTransactionsService(TransactionsRepository transactionsRepository, TransactionsService transactionsService) {
        this.transactionsRepository = transactionsRepository;
        this.transactionsService = transactionsService;
    }

    public TransactionsStatistcs getStatistcs(Integer interval){
        logger.info("Buscando estatisticas");
        List<Transaction> lastTransactions = transactionsService.allTransationsInTheLastSeconds(interval);

        if (lastTransactions.isEmpty()){
            logger.info("Sem Transações");
            return new TransactionsStatistcs(
                            0,
                            0,
                            0,
                            0,
                            0);
        }

        DoubleSummaryStatistics estatisticas = lastTransactions.stream()
                .mapToDouble(transaction -> transaction.getValue().doubleValue()).summaryStatistics();

        logger.info("Busca concluida");

        return  new TransactionsStatistcs(
                estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
