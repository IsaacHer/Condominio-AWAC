package com.API.security.entity;

import javax.persistence.*;

import com.API.datos.entity.Inmuebles;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Basic(optional = false)
    @Column(name = "num_doc")
    private int numDoc;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "telefono")
    private int telefono;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "tokenPassword")
    private String tokenPassword;
    @JoinTable(name = "usuario_rol", joinColumns = {
            @JoinColumn(name = "usuario_id", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "rol_id", referencedColumnName = "ID") })
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rol> roles = new HashSet<>();
    @JsonManagedReference(value = "usuario_inmueble")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Set<Inmuebles> inmueblesSet;

    public Usuario() {
    }

    public Usuario(String tipoDoc, int numDoc, String nombre, String apellido, String nombreUsuario, int telefono, String email, String password) {
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Set<Inmuebles> getInmueblesSet() {
        return inmueblesSet;
    }

    public void setInmueblesSet(Set<Inmuebles> inmueblesSet) {
        this.inmueblesSet = inmueblesSet;
    }
    
}
