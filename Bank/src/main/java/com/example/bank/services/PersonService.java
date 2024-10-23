package com.example.bank.services;

import com.example.bank.models.Person;
import com.example.bank.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        Person newPerson = new Person();

        newPerson.setName(person.getName());
        newPerson.setAge(person.getAge());
        newPerson.setEmail(person.getEmail());

        return personRepository.save(newPerson);
    }

    public Person updatePerson(int id, Person person) {
        Person savedPerson = findPerson(id);

        String name = person.getName();
        String email = person.getEmail();

        if  (!StringUtils.isEmpty(name))
            savedPerson.setName(name);

        if  (!StringUtils.isEmpty(email))
            savedPerson.setName(email);

        return personRepository.save(savedPerson);
    }

    public Person getPerson(int id) {
        return findPerson(id);
    }

    public Person getPerson(String name) {
        return findPerson(name);
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Page<Person> getAllPeople(Pageable page) {
        return personRepository.findAll(page);
    }


    public void deletePerson(String name) {
        Person findedPerson = findPerson(name);
        personRepository.delete(findedPerson);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    private Person findPerson(int id) {
        return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private Person findPerson(String name) {
        return personRepository.findPersonByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public static Person findPerson(int id, PersonRepository personRepository) {
        return personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public static Person findPerson(String name, PersonRepository personRepository) {
        return personRepository.findPersonByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
