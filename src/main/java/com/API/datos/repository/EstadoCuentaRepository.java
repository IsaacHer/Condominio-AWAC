package com.API.datos.repository;

import java.util.Optional;

import com.API.datos.entity.EstadoCuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCuentaRepository  extends JpaRepository<EstadoCuenta, Integer>{
    Optional<EstadoCuenta> findByEstado(String estado);
    boolean existsByEstado(String estado);
}
