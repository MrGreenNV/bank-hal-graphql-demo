package com.example.bank.controllers;

import com.example.bank.models.Card;
import com.example.bank.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Card>> createCard(@RequestBody Card newCard) {
        return ResponseEntity.ok(EntityModel.of(cardService.createCard(newCard)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Card>> updateCard(@PathVariable int id, @RequestBody Card updateCard) {
        return ResponseEntity.ok(EntityModel.of(cardService.updateCard(id, updateCard)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Card>> getCard(@PathVariable int id) {
        return ResponseEntity.ok(EntityModel.of(cardService.getCard(id)));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Card>> getAllCards(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(value = "size", defaultValue = "5", required = false) int pageSize) {

        Page<Card> cardPage = cardService.getAllCards(PageRequest.of(page, pageSize));
        List<Card> cards = cardPage.getContent().stream().toList();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(pageSize, cardPage.getNumber(), cardPage.getTotalElements(), cardPage.getTotalPages());

        return ResponseEntity.ok(PagedModel.of(cards, metadata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable int id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}