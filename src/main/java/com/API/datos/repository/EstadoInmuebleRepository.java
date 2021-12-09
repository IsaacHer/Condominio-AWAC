package com.API.datos.repository;

import java.util.Optional;

import com.API.datos.emum.EnumEstadoInmueble;
import com.API.datos.entity.EstadoInmueble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EstadoInmuebleRepository extends JpaRepository<EstadoInmueble, Integer> {
    Optional<EstadoInmueble> findByenumEstadoInmueble(EnumEstadoInmueble estado);
}
    
