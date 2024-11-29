package com.example.SpringPostgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    // Create a person
    @PostMapping("addPerson")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person response = personRepo.save(person);
        return ResponseEntity.ok(response);
    }

    // Create multiple person
    @PostMapping("addAll")
    public List<Person> allAll(@RequestBody List<Person> allPeron){
        personRepo.saveAll(allPeron);
        return allPeron;
    }

    // Get all persons
    @GetMapping("fetchAll")
    public ResponseEntity<List<Person>> getAllPersonList() {
        List<Person> persons = personRepo.findAll();
        return ResponseEntity.ok(persons);
    }

    // Get person by id
    @GetMapping("fetchPerson/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update person by id
    @PutMapping("update/{id}")
    public ResponseEntity<Person> updatePersonById(@PathVariable long id, @RequestBody Person updatedPerson){
        return personRepo.findById(id).map(existingPerson -> {
            existingPerson.setName(updatedPerson.getName());
            Person savedPerson = personRepo.save(existingPerson);
            return ResponseEntity.ok(savedPerson);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a person by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deletePersonById(@PathVariable long id){
        return personRepo.findById(id).map(person -> {
            personRepo.delete(person);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete all the person
    @DeleteMapping("deleteAll")
    public ResponseEntity<Void> deleteAllPerson(){
        personRepo.deleteAll();
        return ResponseEntity.noContent().build();
    }
}


