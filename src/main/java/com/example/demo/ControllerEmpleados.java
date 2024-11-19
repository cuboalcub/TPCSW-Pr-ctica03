/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author abisai
 */
@RestController
@RequestMapping("/api/empleados")
public class ControllerEmpleados {
    
    
    @Autowired
    private RepositoryEmpleado repositoryEmpleado;
    
    @GetMapping()
    public List<Empleado> list() {
        return  repositoryEmpleado.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable String id) {
                Long empleadoId = Long.valueOf(id);
        Optional<Empleado> empleado = repositoryEmpleado.findById(empleadoId);

        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
        }
}

@PutMapping("/{id}")
public ResponseEntity<?> put(@PathVariable String id, @RequestBody Empleado input) {
    Long empleadoId = Long.valueOf(id); // Convierte el ID de String a Long
    Optional<Empleado> existingEmpleado = repositoryEmpleado.findById(empleadoId);  // Busca al empleado

    if (existingEmpleado.isPresent()) {
        Empleado empleadoToUpdate = existingEmpleado.get();
        
        // Actualiza los atributos del empleado con los datos recibidos en el input
        empleadoToUpdate.setNombre(input.getNombre());
        empleadoToUpdate.setDireccion(input.getDireccion());
        empleadoToUpdate.setTelefono(input.getTelefono());
    
        empleadoToUpdate.setDepto(input.getDepto());
        

        // Guarda los cambios en la base de datos
        Empleado updatedEmpleado = repositoryEmpleado.save(empleadoToUpdate);

        return ResponseEntity.ok(updatedEmpleado);  // Devuelve el empleado actualizado
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
    }
}

    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Empleado input) {
        Empleado nuevoEmpleado = repositoryEmpleado.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }
    
@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable String id) {
    Long empleadoId = Long.valueOf(id); 
    Optional<Empleado> entity = repositoryEmpleado.findById(empleadoId);  
    if (entity.isPresent()) {
        repositoryEmpleado.deleteById(empleadoId);  
        return ResponseEntity.noContent().build();  
    } else {
        return ResponseEntity.notFound().build();  
    }
}



}
