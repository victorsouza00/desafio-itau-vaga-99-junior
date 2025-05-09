package com.victorsouza183.Desafio.JavaSpring.Repository;


import com.victorsouza183.Desafio.JavaSpring.Exceptions.UnprocessableTransactions;
import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class TransactionsRepository {

    private final List<Transaction> transactions= new ArrayList<>();
    public final Logger logger = Logger.getLogger(TransactionsRepository.class.getName());



    public List<Transaction> getTransactions() {
        logger.info("Iniciando busca por transações");
        logger.info("Busca concluida");
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
        logger.info("Transação concluida");
    }

    public void removeAllTransactions(){
        logger.info("Iniciando remoção de Transactions");
        transactions.clear();
        logger.info("Transações deletadas");
    }

}
