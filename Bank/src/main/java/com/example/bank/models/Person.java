package com.example.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private Set<Account> accounts;

    public Person() {
    }

    public Person(int id, Date createdAt, Date updatedAt, String name, int age, String email, Set<Account> accounts) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.age = age;
        this.email = email;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
