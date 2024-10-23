package com.example.bank.resolvers;

import com.example.bank.models.Person;
import com.example.bank.services.PersonService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class PersonMutationResolver {

    private final PersonService personService;

    @Autowired
    public PersonMutationResolver(PersonService personService) {
        this.personService = personService;
    }

    @DgsMutation
    public Person createPerson(String name, int age, String email) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setEmail(email);
        return personService.createPerson(person);
    }

    @DgsMutation
    public Person updatePerson(int id, String name, Integer age, String email) {
        Person person = new Person();
        person.setName(name);
        if (age != null) {
            person.setAge(age);
        }
        person.setEmail(email);
        return personService.updatePerson(id, person);
    }

    @DgsMutation
    public Boolean deletePerson(int id) {
        try {
            personService.deletePerson(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
