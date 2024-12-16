package ru.afilonov.restservice.services;

import ru.afilonov.restservice.models.Account;
import ru.afilonov.restservice.repositories.AccountRepository;
import ru.afilonov.restservice.repositories.PersonRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static ru.afilonov.restservice.services.PersonService.findPerson;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public Account createAccount(Account account) {
        Account newAccount = new Account();

        newAccount.setNumber(account.getNumber());
        newAccount.setBalance(account.getBalance());
        newAccount.setCards(account.getCards());
        newAccount.setOwner(findPerson(account.getOwner().getId(), personRepository));

        return accountRepository.save(newAccount);
    }

    public Account updateAccount(int id, Account account) {
        Account savedAccount = findAccount(id);

        String number = account.getNumber();
        double balance = account.getBalance();

        savedAccount.setNumber(number);
        savedAccount.setBalance(balance);

        return accountRepository.save(savedAccount);
    }

    public Account getAccount(int id) {
        return findAccount(id);
    }

    public Page<Account> getAllAccounts(Pageable page) {
        return accountRepository.findAll(page);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(int id) {
        Account findAccount = findAccount(id);
        accountRepository.delete(findAccount);
    }

    private Account findAccount(int id) {
        return accountRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public static Account findAccount(int id, AccountRepository accountRepository) {
        return accountRepository.findById(id).orElseThrow(EntityExistsException::new);
    }
}
