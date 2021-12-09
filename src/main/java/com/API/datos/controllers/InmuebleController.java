package com.API.datos.controllers;

import java.util.List;

import com.API.datos.entity.Inmuebles;
import com.API.datos.entity.Mensaje;
import com.API.datos.services.InmuebleService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // @ApiOperation("Crea un Inmueble")
    // @PreAuthorize("hasRole('ADMIN')")
    // @PostMapping
    // public ResponseEntity<?> create(@RequestBody Inmuebles inmueble){
    //     // if(StringUtils.isBlank(inmueble.getDireccion()))
    //     //     return new ResponseEntity<>(new Mensaje("la direccion es obligatoria"), HttpStatus.BAD_REQUEST);
    //     // if(inmuebleService.existsById(inmueble.getId()))
    //     //     return new ResponseEntity<>(new Mensaje("ya existe"), HttpStatus.BAD_REQUEST);
    //     Inmuebles newInmueble = new Inmuebles(inmueble.getDireccion());
    //     inmuebleService.save(newInmueble);
    //     return new ResponseEntity<>(new Mensaje("Inmueble creado"), HttpStatus.OK);
    // }

    @ApiOperation("Guarda un producto")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Inmuebles inmueble){
        // if(StringUtils.isBlank(productoDto.getNombre()))
        //     return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        // if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
        //     return new ResponseEntity<>(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        // if(productoService.existsByNombre(productoDto.getNombre()))
        //     return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Inmuebles newInmueble = new Inmuebles(inmueble.getDireccion());
        
        inmuebleService.save(newInmueble);
        return new ResponseEntity<>(new Mensaje("producto creado"), HttpStatus.OK);
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
