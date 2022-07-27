package com.marvi.test.springboot.app.models;

import com.marvi.test.springboot.app.exceptions.DineroInsuficienteException;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode

public class Cuenta {
    private Long id;
    private String persona;
    private BigDecimal saldo;

    public void debito(BigDecimal saldo) {
        BigDecimal nuevoSaldo = this.saldo.subtract(saldo);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero insuficiente en la cuenta.");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal saldo) {
        this.saldo = this.saldo.add(saldo);
    }

}
