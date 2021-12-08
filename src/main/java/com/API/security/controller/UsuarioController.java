package com.API.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.API.datos.entity.Mensaje;
import com.API.security.entity.Rol;
import com.API.security.entity.Usuario;
import com.API.security.enums.RolNombre;
import com.API.security.jwt.JwtProvider;
import com.API.security.service.RolService;
import com.API.security.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @ApiOperation("Muestra una lista de usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.listar();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra un usuario")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable("id")int id){
        Usuario usuario = usuarioService.getById(id).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiOperation("Borra un usuario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")int id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Actualiza un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id")int id, @RequestBody Usuario usuario){
        usuarioService.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> nuevo(@Valid @RequestBody Usuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);

        Set<Rol> roles = new HashSet<>();
        
        String password = nuevoUsuario.getPassword();

        password = passwordEncoder.encode(nuevoUsuario.getPassword());
        nuevoUsuario.setPassword(password);
        
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("ADMIN"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        nuevoUsuario.setRoles(roles);

        usuarioService.save(nuevoUsuario);
        return new ResponseEntity<>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

}
