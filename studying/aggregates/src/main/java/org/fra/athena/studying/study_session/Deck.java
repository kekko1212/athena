package org.fra.athena.studying.study_session;

import java.util.HashSet;
import org.fra.athena.studying.DomainException;

class Deck {

  private final HashSet<CardAggregate> cardAggregates;

  private Deck(final HashSet<CardAggregate> cardAggregates) {
    if (cardAggregates.size() > 100) {
      throw new DomainException("A deck can contain only up to 100 cards.");
    }

    this.cardAggregates = cardAggregates;
  }

  static Deck newDeck(final HashSet<CardAggregate> cardAggregates) {
    return new Deck(new HashSet<>(cardAggregates));
  }

  static Deck newDeck(final CardAggregate firstCardAggregate) {
    final HashSet<CardAggregate> cardAggregates = new HashSet<>();
    cardAggregates.add(firstCardAggregate);

    return new Deck(cardAggregates);
  }

  HashSet<CardAggregate> getCardAggregates() {
    return this.cardAggregates;
  }

  void addCard(final CardAggregate cardAggregate) {
    if (this.cardAggregates.size() >= 100) {
      throw new DomainException("Cannot add more than 100 cards to a deck.");
    }

    this.cardAggregates.add(cardAggregate);
  }

  boolean containsNoUnfamiliarCards() {
    return !this.cardAggregates.stream().anyMatch(CardAggregate::isUnfamiliar);
  }
}
