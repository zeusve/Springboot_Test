package com.marvi.test.springboot.app.repositories;

import com.marvi.test.springboot.app.models.Banco;

import java.util.List;

public interface BancoRepository {
    List<Banco> findAll();

    Banco findById(Long id);

    void update(Banco banco);

}
