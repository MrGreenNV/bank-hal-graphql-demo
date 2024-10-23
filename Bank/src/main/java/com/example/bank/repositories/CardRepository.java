package com.example.bank.repositories;

import com.example.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findCardByNumber(long number);
}
