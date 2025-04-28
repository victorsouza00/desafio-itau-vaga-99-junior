package com.victorsouza183.Desafio.JavaSpring.Models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

public class Transaction {

    private BigDecimal value;
    private OffsetDateTime dataHora;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
