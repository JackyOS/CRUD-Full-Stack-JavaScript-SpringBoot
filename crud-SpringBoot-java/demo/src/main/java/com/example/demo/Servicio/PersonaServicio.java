package com.example.demo.Servicio;

import com.example.demo.Repo.PersonaRepo;
import com.example.demo.entidad.Persona;
import com.example.demo.exception.ResourceNotFoundException;
import dto.PersonaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<PersonaDto> getAlls(){
        List<Persona> personas = personaRepo.findAll();
        //mapeamos la entidad a dto y lo mostramos en una lista
        return personas.stream().map(persona -> mapperDTO(persona)).collect(Collectors.toList());
    }

    public PersonaDto create(PersonaDto personaDto){
        Persona persona = mapperEntity(personaDto);
        Persona newPersona = personaRepo.save(persona);
        PersonaDto personaRes = mapperDTO(newPersona);
        return personaRes;
    }

    public PersonaDto update(PersonaDto personaDto, long id){
        Persona viejapersona = personaRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Persona", "id", id));
        viejapersona.setNombre(personaDto.getNombre());
        viejapersona.setEdad(personaDto.getEdad());
        Persona nuevaPersona = personaRepo.save(viejapersona);
        return mapperDTO(nuevaPersona);
    }

    public void delete(long id){
        Persona persona = personaRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Persona", "id", id));
        personaRepo.delete(persona);
    }


    //convierte la entidad a dto
    public PersonaDto mapperDTO(Persona persona){
        PersonaDto personaDto = modelMapper.map(persona, PersonaDto.class);
        return personaDto;
    }

    //convierte dto a entidad
    public Persona mapperEntity(PersonaDto personaDTO){
        Persona persona = modelMapper.map(personaDTO, Persona.class);
        return persona;
    }



}
