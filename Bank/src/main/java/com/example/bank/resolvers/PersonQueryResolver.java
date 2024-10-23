package com.example.bank.resolvers;

import com.example.bank.models.Person;
import com.example.bank.services.PersonService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class PersonQueryResolver {

    private final PersonService personService;

    @Autowired
    public PersonQueryResolver(PersonService personService) {
        this.personService = personService;
    }

    @DgsQuery
    public Person getPerson(int id) {
        return personService.getPerson(id);
    }

    @DgsQuery
    public List<Person> allPeople() {
        return personService.getAllPeople();
    }
}
