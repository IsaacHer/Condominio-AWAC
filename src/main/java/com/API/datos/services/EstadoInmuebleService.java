package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.entity.EstadoInmueble;
import com.API.datos.repository.EstadoInmuebleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadoInmuebleService {
    
    @Autowired
    EstadoInmuebleRepository estadoInmuebleRepository;

    public List<EstadoInmueble> list() {
        return estadoInmuebleRepository.findAll();
    }

    public Optional<EstadoInmueble> getOne(int id) {
        return estadoInmuebleRepository.findById(id);
    }

    public void save(EstadoInmueble estadoInmueble){
        estadoInmuebleRepository.save(estadoInmueble);
    }

    public void delete(int id){
        estadoInmuebleRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return estadoInmuebleRepository.existsById(id);
    }

}
