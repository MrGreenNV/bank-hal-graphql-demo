package ru.afilonov.restservice.controllers;

import ru.afilonov.restservice.models.Person;
import ru.afilonov.restservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Person>> createPerson(@RequestBody Person newPerson) {
        return ResponseEntity.ok(EntityModel.of(personService.createPerson(newPerson)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Person>> updatePerson(@PathVariable int id, @RequestBody Person updatePerson) {
        return ResponseEntity.ok(EntityModel.of(personService.updatePerson(id, updatePerson)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Person>> getPerson(@PathVariable int id) {
        return ResponseEntity.ok(EntityModel.of(personService.getPerson(id)));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Person>> getAllPeople(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                           @RequestParam(value = "size", defaultValue = "5", required = false) int pageSize) {

        Page<Person> personPage = personService.getAllPeople(PageRequest.of(page, pageSize));
        List<Person> people = personPage.getContent().stream().toList();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(pageSize, personPage.getNumber(), personPage.getTotalElements(), personPage.getTotalPages());

        return ResponseEntity.ok(PagedModel.of(people, metadata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
