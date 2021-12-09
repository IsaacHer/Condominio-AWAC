/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.API.datos.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import com.API.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author David Alberto Mora Peñaranda
 */
@Entity
@Table(name = "inmuebles")
@NamedQueries({
    @NamedQuery(name = "Inmuebles.findAll", query = "SELECT i FROM Inmuebles i"),
    @NamedQuery(name = "Inmuebles.findById", query = "SELECT i FROM Inmuebles i WHERE i.id = :id"),
    @NamedQuery(name = "Inmuebles.findByDireccion", query = "SELECT i FROM Inmuebles i WHERE i.direccion = :direccion")})
public class Inmuebles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    

    @JoinTable(name = "inmueble_estadoInmueble", joinColumns = {
            @JoinColumn(name = "inmueble_id", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "estadoInmueble_id", referencedColumnName = "ID") })
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<EstadoInmueble> estadoInmueble = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID")
   // @JsonBackReference(value="usuario_inmueble")
    private Usuario usuario;

    public Inmuebles() {
    }


    public Inmuebles(String direccion) {
        this.direccion = direccion;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
    

    public static long getSerialversionuid() {
        return serialVersionUID;
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    
    public Set<EstadoInmueble> getEstadoInmueble() {
        return estadoInmueble;
    }

    public void setEstadoInmueble(Set<EstadoInmueble> estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
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
        if (!(object instanceof Inmuebles)) {
            return false;
        }
        Inmuebles other = (Inmuebles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.resources.newpackage.Inmuebles[ id=" + id + " ]";
    }
    
}
