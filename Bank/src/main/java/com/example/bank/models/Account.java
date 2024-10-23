package com.example.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private double balance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    public Account() {
    }

    public Account(int id, Date createdAt, Date updatedAt, String number, double balance, Person owner, List<Card> cards) {
        super(id, createdAt, updatedAt);
        this.number = number;
        this.balance = balance;
        this.owner = owner;
        this.cards = cards;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
