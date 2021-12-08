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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @JsonIgnore
    @JsonBackReference(value = "inmuebles")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInmueble")
    private List<Cuenta> cuentaList;
    @JoinColumn(name = "estado_id", referencedColumnName = "ID")
    @JsonBackReference(value="estadoInmueble")
    @ManyToOne(optional = false)
    private EstadoInmueble estadoId;
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID")
    @JsonBackReference(value="usuario_inmueble")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Inmuebles() {
    }

    public Inmuebles(Integer id) {
        this.id = id;
    }

    public Inmuebles(String direccion) {
        this.direccion = direccion;
    }

    public Inmuebles(Integer id, String direccion) {
        this.id = id;
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

    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    public EstadoInmueble getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadoInmueble estadoId) {
        this.estadoId = estadoId;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
