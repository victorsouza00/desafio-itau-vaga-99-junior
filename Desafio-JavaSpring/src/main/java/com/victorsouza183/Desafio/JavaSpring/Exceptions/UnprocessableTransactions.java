package com.victorsouza183.Desafio.JavaSpring.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableTransactions extends RuntimeException {
    public UnprocessableTransactions(String message) {
        super(message);
    }
}
