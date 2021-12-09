package com.API.datos.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.API.datos.emum.EnumEstadoInmueble;
import com.API.datos.entity.EstadoInmueble;
import com.API.datos.entity.Inmuebles;
import com.API.datos.entity.Mensaje;
import com.API.datos.services.EstadoInmuebleService;
import com.API.datos.services.InmuebleService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/inmuebles")
@CrossOrigin(origins = "*")
public class InmuebleController {
    
    @Autowired
    InmuebleService inmuebleService;

      @Autowired
    EstadoInmuebleService estadoService;

    @ApiOperation("Muestra una lista Inmuebles")
    @GetMapping
    public ResponseEntity<List<Inmuebles>> list(){
        List<Inmuebles> list = inmuebleService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Obtiene un Inmueble por su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!inmuebleService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Inmuebles inmueble = inmuebleService.getOne(id).get();
        return new ResponseEntity<>(inmueble, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
    public ResponseEntity<?> nuevo(@Valid @RequestBody Inmuebles nuevoInmueble, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        // if(inmuebleService.existsByNombreUsuario(nuevoInmueble.getDireccion()))
        //     return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        // if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
        //     return new ResponseEntity<>(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);

        Set<EstadoInmueble> estados = new HashSet<>();
                
        estados.add(estadoService.findByEnumEstadoInmueble(EnumEstadoInmueble.VACIO).get());
        if(nuevoInmueble.getEstadoInmueble().contains("ADMIN"))
            estados.add(estadoService.findByEnumEstadoInmueble(EnumEstadoInmueble.OCUPADO).get());
        nuevoInmueble.setEstadoInmueble(estados);

        inmuebleService.save(nuevoInmueble);
        return new ResponseEntity<>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @ApiOperation("Actualiza un Inmueble")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Inmuebles inmueble){
        if(!inmuebleService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(inmuebleService.existsByDireccion(inmueble.getDireccion()))
            return new ResponseEntity<>(new Mensaje("esa direccion ya existe"), HttpStatus.BAD_REQUEST);
        inmuebleService.save(inmueble);
        return new ResponseEntity<>(new Mensaje("Inmueble actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un Inmueble")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!inmuebleService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        inmuebleService.delete(id);
        return new ResponseEntity<>(new Mensaje("Inmueble eliminado"), HttpStatus.OK);
    }

    //    @ApiOperation("Obtiene un listado de Inmuebles por su estado")
    // @GetMapping("/{estado}")
    // public ResponseEntity<?> getById(@PathVariable("id") int id){
    //     if(!inmuebleService.existsById(id)){
    //         return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
    //     }
    //     Inmuebles inmueble = inmuebleService.getOne(id).get();
    //     return new ResponseEntity<>(inmueble, HttpStatus.OK);
    // }
}
