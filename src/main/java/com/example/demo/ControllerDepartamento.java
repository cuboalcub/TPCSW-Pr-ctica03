package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
public class ControllerDepartamento {

    @Autowired
    private RepositoryDepartamento repositoryDepartamento;

    // Obtener todos los departamentos
    @GetMapping
    public Iterable<Departamento> list() {
        return repositoryDepartamento.findAll();
    }

    // Obtener un departamento por clave
    @GetMapping("/{clave}")
    public ResponseEntity<Object> get(@PathVariable Long clave) {
        Optional<Departamento> departamento = repositoryDepartamento.findById(clave);
        if (departamento.isPresent()) {
            return ResponseEntity.ok(departamento.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }

    // Crear un nuevo departamento
    @PostMapping
    public ResponseEntity<Departamento> post(@RequestBody Departamento nuevoDepartamento) {
        Departamento departamentoCreado = repositoryDepartamento.save(nuevoDepartamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoCreado);
    }

    // Actualizar un departamento existente por clave
    @PutMapping("/{clave}")
    public ResponseEntity<Object> put(@PathVariable Long clave, @RequestBody Departamento departamentoActualizado) {
        Optional<Departamento> existingDepartamento = repositoryDepartamento.findById(clave);
        if (existingDepartamento.isPresent()) {
            Departamento departamentoToUpdate = existingDepartamento.get();
            departamentoToUpdate.setNombre(departamentoActualizado.getNombre());
            Departamento updatedDepartamento = repositoryDepartamento.save(departamentoToUpdate);
            return ResponseEntity.ok(updatedDepartamento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }

    // Eliminar un departamento por clave
    @DeleteMapping("/{clave}")
    public ResponseEntity<Object> delete(@PathVariable Long clave) {
        Optional<Departamento> departamento = repositoryDepartamento.findById(clave);
        if (departamento.isPresent()) {
            repositoryDepartamento.deleteById(clave);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }
}
