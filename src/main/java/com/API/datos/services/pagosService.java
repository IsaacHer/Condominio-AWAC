package com.API.datos.services;

import java.util.List;
import java.util.Optional;

import com.API.datos.entity.Pagos;
import com.API.datos.repository.PagosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class pagosService {
    
    @Autowired
    PagosRepository pagosRepository;

    public List<Pagos> list(){
        return pagosRepository.findAll();
    }

    public Optional<Pagos> getOne(int id){
        return pagosRepository.findById(id);
    }

    // public Optional<Pagos> getByNombre(String nombre){
    //     return pagosRepository.findByNombre(nombre);
    // }

    public void  save(Pagos pago){
        pagosRepository.save(pago);
    }

    public void delete(int id){
        pagosRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return pagosRepository.existsById(id);
    }

}
