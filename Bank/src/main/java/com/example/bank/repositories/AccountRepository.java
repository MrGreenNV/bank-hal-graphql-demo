package com.example.bank.repositories;

import com.example.bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
