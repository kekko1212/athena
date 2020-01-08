package org.fra.athena.studying.domain.model.study_session;

import java.time.LocalDateTime;
import java.util.HashSet;
import org.fra.athena.studying.domain.model.card.CardAggregate;
import org.fra.athena.studying.domain.model.common.DomainException;

public class StudySessionAggregate {

  private final StudySessionRootEntity rootEntity;

  private StudySessionAggregate(final Deck deck) {
    this.rootEntity = new StudySessionRootEntity(deck);
  }

  public static StudySessionAggregate startStudySession(final Deck deck) {
    if (deck.containsNoUnfamiliarCards()) {
      throw new DomainException("The selected deck does not contain any unfamiliar card.");
    }

    return new StudySessionAggregate(deck);
  }

  public boolean isOngoing() {
    return this.rootEntity.isOngoing();
  }

  public void endStudySession() {
    this.rootEntity.endStudySession(LocalDateTime.now());
  }

  public HashSet<CardAggregate> getCardsToStudy() {
    return this.rootEntity.getDeck().getCards();
  }
}
