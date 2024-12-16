package ru.afilonov.restservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.afilonov.restservice.models.Account;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
