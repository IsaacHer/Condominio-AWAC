package com.API.datos.controllers;

import java.util.List;

import com.API.datos.entity.EstadoCuenta;
import com.API.datos.entity.Mensaje;
import com.API.datos.services.EstadoCuentaService;

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
@RequestMapping("/estadoCuenta")
@CrossOrigin(origins = "*")
public class EstadoCuentaController {
    
    @Autowired
    EstadoCuentaService estadoCuentaService;

    @ApiOperation("Muestra una lista de Estados de cuenta")
    @GetMapping
    public ResponseEntity<List<EstadoCuenta>> list(){
        List<EstadoCuenta> list = estadoCuentaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!estadoCuentaService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        EstadoCuenta estadoCuenta = estadoCuentaService.getOne(id).get();
        return new ResponseEntity<>(estadoCuenta, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> getByNombre(@PathVariable("estado") String estado){
        if(!estadoCuentaService.existsByEstado(estado))
            return new ResponseEntity<>(new Mensaje("Ese tipo de estado de cuenta ya existe"), HttpStatus.NOT_FOUND);
        EstadoCuenta estadoCuenta = estadoCuentaService.findByEstado(estado).get();
        return new ResponseEntity<>(estadoCuenta, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EstadoCuenta estadoCuenta){
        if(StringUtils.isBlank(estadoCuenta.getEstado()))
            return new ResponseEntity<>(new Mensaje("El estado de cuenta no puede estar vacio"), HttpStatus.BAD_REQUEST);
        if(estadoCuentaService.existsByEstado(estadoCuenta.getEstado()))
            return new ResponseEntity<>(new Mensaje("Ese estado de cuenta ya existe"), HttpStatus.NOT_FOUND);

        estadoCuentaService.save(estadoCuenta);
        return new ResponseEntity<>(new Mensaje("Estado de cuenta creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EstadoCuenta estadoCuenta){
        if(!estadoCuentaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(estadoCuentaService.existsByEstado(estadoCuenta.getEstado()))
             return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(estadoCuenta.getEstado()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        estadoCuentaService.save(estadoCuenta);
        return new ResponseEntity<>(new Mensaje("Estado de cuenta actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!estadoCuentaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        estadoCuentaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Estado de cuenta eliminado"), HttpStatus.OK);
    }

}
