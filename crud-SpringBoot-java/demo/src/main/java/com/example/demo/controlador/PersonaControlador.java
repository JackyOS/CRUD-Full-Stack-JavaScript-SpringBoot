package com.example.demo.controlador;

import com.example.demo.Servicio.PersonaServicio;
import com.example.demo.entidad.Persona;
import dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://127.0.0.1:5501")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/personas")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping
    public ResponseEntity<List<PersonaDto>> getPersona() {
        return ResponseEntity.ok().body(personaServicio.getAlls());
    }

    @PostMapping
    public ResponseEntity<PersonaDto> addPersona(@Valid @RequestBody PersonaDto personaDto){
        return new ResponseEntity<>(personaServicio.create(personaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto>  updatePersona(@Valid @RequestBody PersonaDto personaDto, @PathVariable long id){
        return new ResponseEntity<>(personaServicio.update(personaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable long id) {
        personaServicio.delete(id);
        return new ResponseEntity<>("Persona borrada correctamente", HttpStatus.OK);
    }



}
