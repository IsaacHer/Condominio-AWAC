package com.API.datos.repository;

import java.util.Optional;

import com.API.datos.entity.Inmuebles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InmuebleRepository extends JpaRepository<Inmuebles, Integer> {
    Boolean existsByDireccion(String direccion);
    Optional<Inmuebles> findByDireccion(String direccion);
    //Optional<Inmuebles> findByEstadoId(Integer estadoId );
}
    
