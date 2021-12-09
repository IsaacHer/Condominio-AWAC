/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.API.datos.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.API.datos.emum.EnumEstadoInmueble;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author David Alberto Mora Peñaranda
 */
@Entity
@Table(name = "estado_inmueble")
@NamedQueries({
    @NamedQuery(name = "EstadoInmueble.findAll", query = "SELECT e FROM EstadoInmueble e"),
    @NamedQuery(name = "EstadoInmueble.findById", query = "SELECT e FROM EstadoInmueble e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoInmueble.findByEstado", query = "SELECT e FROM EstadoInmueble e WHERE e.estado = :estado")})

public class EstadoInmueble {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  @NotNull
    @Enumerated(EnumType.STRING)
    private EnumEstadoInmueble estado;

    

    public EstadoInmueble() {
    }

     public EstadoInmueble(@NotNull EnumEstadoInmueble estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public EnumEstadoInmueble getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoInmueble estado) {
        this.estado = estado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoInmueble)) {
            return false;
        }
        EstadoInmueble other = (EstadoInmueble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.resources.newpackage.EstadoInmueble[ id=" + id + " ]";
    }
    
}
