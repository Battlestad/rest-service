package com.example.restservice.controller;

import com.example.restservice.dao.PersonDao;
import com.example.restservice.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class PersonController
{
    private final PersonDao personDao;

    public PersonController(PersonDao personDao) { this.personDao = personDao; }

    @GetMapping(value = "/persons")
    public List<PersonDto> persons(@RequestParam(name="postNr", required=false) Integer postNr) throws InterruptedException {
        System.out.println(postNr);
        if (postNr != null) {
            return personDao.getPersonsByPostnr(postNr);
        } else {
            return personDao.getPersons();
        }
    }

    @GetMapping(value = "/persons/{id}")
    public PersonDto person(@PathVariable int id) throws InterruptedException {
        return personDao.getPerson(id).get();
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<PersonDto> addPerson(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personDao.addPerson(personDto));
    }

}
