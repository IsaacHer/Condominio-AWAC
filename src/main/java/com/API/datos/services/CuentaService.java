package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.entity.Cuenta;
import com.API.datos.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    public List<Cuenta> getCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> getCuenta(int id) {
        return cuentaRepository.findById(id);
    }

    public void save(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public void delete(int id) {
        cuentaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return cuentaRepository.existsById(id);
    }

    public Cuenta getCuentaByID(Integer integer) {
        return cuentaRepository.findById(integer).get();
    }

}
