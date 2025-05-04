package com.victorsouza183.Desafio.JavaSpring.Controllers;


import com.victorsouza183.Desafio.JavaSpring.Models.Transaction;
import com.victorsouza183.Desafio.JavaSpring.Models.TransactionsStatistcs;
import com.victorsouza183.Desafio.JavaSpring.Services.StatistcsTransactionsService;
import com.victorsouza183.Desafio.JavaSpring.Services.TransactionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsavel por adiconar Transactions")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Transactions registrada"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Campos inválidos "
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de requisição"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro de Servidor"
            )
    })
    public ResponseEntity<Void> addTransactions(
            @RequestBody Transaction transaction
    ){
        transactionsService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Endpoint responsavel buscar Transactions em um certo intervalo de tempo")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Busca de estatísticas completa"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao buscar estatisticas"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro de Servidor"
            )
    })
    @GetMapping("/transacoes")
    public List<Transaction> getTransactionsInTheLastSeconds(
            @RequestParam Integer interval
    ){
        return transactionsService.allTransationsInTheLastSeconds(interval);
    }


    @Operation(description = "Endpoint responsavel por remover Transactions")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Transactions Deletada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de requisição"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro de Servidor"
            )
    })
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> removeTransactions(){
        transactionsService.removeAllTransactions();
        return ResponseEntity.ok().build();
    }



    @Operation(description = "Endpoint responsavel por retornar estatisticas de Transacitons")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Busca de estatísticas completa"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao buscar estatisticas"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro de Servidor"
            )
    })
    @GetMapping("/estatistica")
    public ResponseEntity<TransactionsStatistcs> getTransactionsStatistcs(
            @RequestParam(required = false,defaultValue = "60")
            Integer interval
    ){
        return ResponseEntity.ok(statistcsTransactionsService.getStatistcs(interval));
    }


}
