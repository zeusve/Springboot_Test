package com.marvi.test.springboot.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Banco {
    private Long id;
    private String nombre;
    private int totalTransferencias;
}
