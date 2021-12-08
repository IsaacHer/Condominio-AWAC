package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.entity.Inmuebles;
import com.API.datos.repository.InmuebleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InmuebleService {
    
    @Autowired
    InmuebleRepository inmuebleRepository;

    public List<Inmuebles> list(){
        return inmuebleRepository.findAll();
    }

    public Optional<Inmuebles> getOne(int id){
        return inmuebleRepository.findById(id);
    }

    public Optional<Inmuebles> getByDireccion(String direccion){
        return inmuebleRepository.findByDireccion(direccion);
    }

    public void  save(Inmuebles inmueble){
        inmuebleRepository.save(inmueble);
    }

    public void delete(int id){
        inmuebleRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return inmuebleRepository.existsById(id);
    }

    public Boolean existsByDireccion(String direccion) {
        return  inmuebleRepository.existsByDireccion(direccion);
    }

}
