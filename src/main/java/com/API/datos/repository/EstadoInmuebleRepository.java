package com.API.datos.repository;

import com.API.datos.entity.EstadoInmueble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EstadoInmuebleRepository extends JpaRepository<EstadoInmueble, Integer> {
}
    
