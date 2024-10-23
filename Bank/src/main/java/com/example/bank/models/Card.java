package com.example.bank.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {
    @Column(name = "number")
    private long number;

    @Column(name = "cvc")
    private int cvc;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Card() {
    }

    public Card(int id, Date createdAt, Date updatedAt, long number, int cvc, Date endDate, Account account) {
        super(id, createdAt, updatedAt);
        this.number = number;
        this.cvc = cvc;
        this.endDate = endDate;
        this.account = account;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
