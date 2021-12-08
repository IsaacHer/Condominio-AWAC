/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.API.datos.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author David Alberto Mora Peñaranda
 */
@Entity
@Table(name = "estado_cuenta")
@NamedQueries({
    @NamedQuery(name = "EstadoCuenta.findAll", query = "SELECT e FROM EstadoCuenta e"),
    @NamedQuery(name = "EstadoCuenta.findById", query = "SELECT e FROM EstadoCuenta e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoCuenta.findByEstado", query = "SELECT e FROM EstadoCuenta e WHERE e.estado = :estado")})
public class EstadoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JsonIgnore
    @JsonManagedReference(value = "cuenta_estado")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoId")
    private List<Cuenta> cuentaList;

    public EstadoCuenta() {
    }

    public EstadoCuenta(Integer id) {
        this.id = id;
    }

    public EstadoCuenta(Integer id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
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
        if (!(object instanceof EstadoCuenta)) {
            return false;
        }
        EstadoCuenta other = (EstadoCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.resources.newpackage.EstadoCuenta[ id=" + id + " ]";
    }
    
}
