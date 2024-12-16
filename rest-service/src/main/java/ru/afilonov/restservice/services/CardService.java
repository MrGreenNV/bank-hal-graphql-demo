package ru.afilonov.restservice.services;

import ru.afilonov.restservice.models.Card;
import ru.afilonov.restservice.repositories.AccountRepository;
import ru.afilonov.restservice.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static ru.afilonov.restservice.services.AccountService.findAccount;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public Card createCard(Card card) {
        Card newCard = new Card();

        newCard.setNumber(card.getNumber());
        newCard.setCvc(card.getCvc());
        newCard.setEndDate(card.getEndDate());
        newCard.setAccount(findAccount(card.getId(), accountRepository));

        return cardRepository.save(newCard);
    }

    public Card updateCard(int id, Card card) {
        Card savedCard = findCard(id);

        savedCard.setEndDate(card.getEndDate());
        savedCard.setCvc(card.getCvc());
        savedCard.setAccount(findAccount(card.getAccount().getId(), accountRepository));

        return cardRepository.save(savedCard);
    }

    public Card getCard(int id) {
        return findCard(id);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Page<Card> getAllCards(Pageable page) {
        return cardRepository.findAll(page);
    }

    public void deleteCard(int id) {
        Card findCard = findCard(id);
        cardRepository.delete(findCard);
    }

    private Card findCard(int id) {
        return cardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public static Card findCard(int id, CardRepository cardRepository) {
        return cardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
