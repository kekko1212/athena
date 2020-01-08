package org.fra.athena.studying.domain.model.study_session;

import java.time.LocalDateTime;
import java.util.Optional;
import org.fra.athena.studying.domain.model.common.DomainException;

class StudySessionRootEntity {
  private final Deck deck;
  private LocalDateTime endTime = null;

  StudySessionRootEntity(final Deck deck) {
    this.deck = deck;
  }

  boolean isOngoing() {
    return this.getEndTime().isEmpty();
  }

  Deck getDeck() {
    return this.deck;
  }

  void endStudySession(final LocalDateTime endTime) {
    this.getEndTime().ifPresent(this::throwBecauseStudySessionAlreadyEnded);

    this.endTime = endTime;
  }

  private Optional<LocalDateTime> getEndTime() {
    return Optional.ofNullable(this.endTime);
  }

  private void throwBecauseStudySessionAlreadyEnded(final LocalDateTime localDateTime) {
    throw new DomainException("foo");
  }
}
