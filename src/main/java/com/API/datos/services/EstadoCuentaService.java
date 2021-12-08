package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.entity.EstadoCuenta;
import com.API.datos.repository.EstadoCuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadoCuentaService {
    
      @Autowired
    EstadoCuentaRepository estadoCuentaRepository;

    public List<EstadoCuenta> list(){
        return estadoCuentaRepository.findAll();
    }

    public Optional<EstadoCuenta> getOne(int id){
        return estadoCuentaRepository.findById(id);
    }

    public Optional<EstadoCuenta> findByEstado(String estado){
        return estadoCuentaRepository.findByEstado(estado);
    }

    public void  save(EstadoCuenta estadoCuenta){
        estadoCuentaRepository.save(estadoCuenta);
    }

    public void delete(int id){
        estadoCuentaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return estadoCuentaRepository.existsById(id);
    }

    public boolean existsByEstado(String estado) {
        return estadoCuentaRepository.existsByEstado(estado);
    }
}
