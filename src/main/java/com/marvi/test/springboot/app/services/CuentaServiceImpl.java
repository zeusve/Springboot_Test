package com.marvi.test.springboot.app.services;

import com.marvi.test.springboot.app.models.Banco;
import com.marvi.test.springboot.app.models.Cuenta;
import com.marvi.test.springboot.app.repositories.BancoRepository;
import com.marvi.test.springboot.app.repositories.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {
    private CuentaRepository cuentaRepository;
    private BancoRepository bancoRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findById(Long id) { return cuentaRepository.findById(id).orElseThrow();}

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       cuentaRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public int revisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    @Transactional
    public void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal saldo, Long bancoId) {

        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(saldo);
        cuentaRepository.save(cuentaOrigen);

        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
        cuentaDestino.credito(saldo);
        cuentaRepository.save(cuentaDestino);

        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.save(banco);
    }
}
