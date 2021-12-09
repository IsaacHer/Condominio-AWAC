package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.emum.EnumEstadoInmueble;
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

    public Optional<EstadoInmueble> findByEnumEstadoInmueble(EnumEstadoInmueble estado){
        return estadoInmuebleRepository.findByenumEstadoInmueble(estado);
    }

    public void save(EstadoInmueble estado){
        estadoInmuebleRepository.save(estado);
    }
}
