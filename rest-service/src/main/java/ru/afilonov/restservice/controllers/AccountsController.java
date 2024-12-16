package ru.afilonov.restservice.controllers;

import ru.afilonov.restservice.models.Account;
import ru.afilonov.restservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountService accountService;

    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Account>> createAccount(@RequestBody Account newAccount) {
        return ResponseEntity.ok(EntityModel.of(accountService.createAccount(newAccount)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Account>> updateAccount(@PathVariable int id, @RequestBody Account updateAccount) {
        return ResponseEntity.ok(EntityModel.of(accountService.updateAccount(id, updateAccount)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Account>> getAccount(@PathVariable int id) {
        return ResponseEntity.ok(EntityModel.of(accountService.getAccount(id)));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Account>> getAllAccounts(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                           @RequestParam(value = "size", defaultValue = "5", required = false) int pageSize) {

        Page<Account> accountPage = accountService.getAllAccounts(PageRequest.of(page, pageSize));
        List<Account> accounts = accountPage.getContent().stream().toList();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(pageSize, accountPage.getNumber(), accountPage.getTotalElements(), accountPage.getTotalPages());

        return ResponseEntity.ok(PagedModel.of(accounts, metadata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
