package org.fra.athena.studying.domain.model.study_session;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import org.fra.athena.studying.domain.model.card.CardAggregate;
import org.fra.athena.studying.domain.model.common.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudySessionAggregateTest {

  @Test
  public void aStudyingSessionCanBeStarted() {
    final Deck deck = Deck.newDeck(CardAggregate.newCard("House"));

    final StudySessionAggregate studySessionAggregate =
        StudySessionAggregate.startStudySession(deck);

    Assertions.assertTrue(studySessionAggregate.isOngoing());
  }

  @Test
  void startingStudyingSessionIsNotPossibleIfThereAreNoUnfamiliarCardsInTheSelectedDeck() {
    final CardAggregate cardAggregate = CardAggregate.newCard("House");
    cardAggregate.answer(
        "House", Clock.fixed(Instant.parse("2020-01-01T00:00:00.00Z"), ZoneId.systemDefault()));

    final Deck deck = Deck.newDeck(cardAggregate);

    Assertions.assertThrows(
        DomainException.class, () -> StudySessionAggregate.startStudySession(deck));
  }

  @Test
  public void anOngoingStudyingSessionCanBeEnded() {
    final Deck deck = Deck.newDeck(CardAggregate.newCard("House"));
    final StudySessionAggregate studySessionAggregate =
        StudySessionAggregate.startStudySession(deck);

    Assertions.assertTrue(studySessionAggregate.isOngoing());

    studySessionAggregate.endStudySession();
    Assertions.assertFalse(
        studySessionAggregate.isOngoing(),
        "Studying session expected to be started, but it wasn't.");
  }

  @Test
  public void endingAnAlreadyEndedStudyingSessionResultsInAnException() {
    final Deck deck = Deck.newDeck(CardAggregate.newCard("House"));
    final StudySessionAggregate studySessionAggregate =
        StudySessionAggregate.startStudySession(deck);

    studySessionAggregate.endStudySession();

    Assertions.assertThrows(DomainException.class, studySessionAggregate::endStudySession);
  }

  @Test
  public void canGetTheCardsToStudyFromTheDeck() {
    final Deck deck = Deck.newDeck(CardAggregate.newCard("House"));

    deck.addCard(CardAggregate.newCard("Hill"));
    deck.addCard(CardAggregate.newCard("Tree"));

    final StudySessionAggregate studySessionAggregate =
        StudySessionAggregate.startStudySession(deck);

    final HashSet<CardAggregate> cardsToStudy = studySessionAggregate.getCardsToStudy();

    cardsToStudy.stream().forEach(card -> Assertions.assertTrue(card.isUnfamiliar()));
  }
}
