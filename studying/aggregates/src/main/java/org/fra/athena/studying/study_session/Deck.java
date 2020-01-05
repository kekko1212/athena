package org.fra.athena.studying.study_session;

import java.util.HashSet;
import org.fra.athena.studying.DomainException;
import org.fra.athena.studying.card.CardAggregate;

class Deck {

  private final HashSet<CardAggregate> cards;

  private Deck(final HashSet<CardAggregate> cards) {
    if (cards.size() > 100) {
      throw new DomainException("A deck can contain only up to 100 cards.");
    }

    this.cards = cards;
  }

  static Deck newDeck(final HashSet<CardAggregate> cardAggregates) {
    return new Deck(new HashSet<>(cardAggregates));
  }

  static Deck newDeck(final CardAggregate firstCardAggregate) {
    final HashSet<CardAggregate> cardAggregates = new HashSet<>();
    cardAggregates.add(firstCardAggregate);

    return new Deck(cardAggregates);
  }

  HashSet<CardAggregate> getCards() {
    return this.cards;
  }

  void addCard(final CardAggregate cardAggregate) {
    if (this.cards.size() >= 100) {
      throw new DomainException("Cannot add more than 100 cards to a deck.");
    }

    this.cards.add(cardAggregate);
  }

  boolean containsNoUnfamiliarCards() {
    return !this.cards.stream().anyMatch(CardAggregate::isUnfamiliar);
  }
}
