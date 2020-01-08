package org.fra.athena.studying.domain.model.study_session;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.fra.athena.studying.domain.model.card.CardAggregate;
import org.fra.athena.studying.domain.model.common.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeckTest {

  private static CardAggregate generateCard(final Integer i) {
    return CardAggregate.newCard("House");
  }

  private static HashSet<CardAggregate> generateNCards(final Integer numberOfCards) {
    return IntStream.range(0, numberOfCards)
        .mapToObj(DeckTest::generateCard)
        .collect(Collectors.toCollection(HashSet::new));
  }

  @Test
  void aDeckCannotBeCreatedWithMoreThan100Cards() {
    final HashSet<CardAggregate> moreThan100CardAggregates = DeckTest.generateNCards(101);

    final Exception exception =
        Assertions.assertThrows(
            DomainException.class, () -> Deck.newDeck(moreThan100CardAggregates));
    Assertions.assertEquals("A deck can contain only up to 100 cards.", exception.getMessage());
  }

  @Test
  void cannotAddMoreThan100CardsToADeck() {
    final HashSet<CardAggregate> oneHundredCardAggregates = DeckTest.generateNCards(100);
    final Deck deckWith100Cards = Deck.newDeck(oneHundredCardAggregates);

    final Exception exception =
        Assertions.assertThrows(
            DomainException.class, () -> deckWith100Cards.addCard(CardAggregate.newCard("House")));
    Assertions.assertEquals("Cannot add more than 100 cards to a deck.", exception.getMessage());
  }

  @Test
  void canRetrieveCardsFromDeck() {
    final Deck deck = Deck.newDeck(CardAggregate.newCard("House"));

    Assertions.assertNotNull(deck.getCards());
    Assertions.assertEquals(1, deck.getCards().size());
  }

  @Test
  void containsNoUnfamiliarCards() {
    final CardAggregate cardAggregate = CardAggregate.newCard("House");
    cardAggregate.answer(
        "House", Clock.fixed(Instant.parse("2020-01-01T00:00:00.00Z"), ZoneId.systemDefault()));

    final Deck deck = Deck.newDeck(cardAggregate);

    Assertions.assertTrue(
        deck.containsNoUnfamiliarCards(), "Deck expected to not contain any unfamiliar card.");
  }
}
