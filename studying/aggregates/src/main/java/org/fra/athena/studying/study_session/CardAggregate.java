package org.fra.athena.studying.study_session;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

class CardAggregate {

  CardRootEntity rootEntity;
  LinkedList<Answer> answers = new LinkedList<>();

  private CardAggregate(final Term term, final CardType type) {
    this.rootEntity = new CardRootEntity(type, term);
  }

  public static CardAggregate newCard(final Term term) {
    return new CardAggregate(term, CardType.UNFAMILIAR);
  }

  Result answer(final Answer answer) {
    this.answers.add(answer);

    if (this.rootEntity.isCorrect(answer)) {
      this.rootEntity.processCorrectAnswer();

      return Result.CORRECT;
    }

    this.rootEntity.processWrongAnswer();

    return Result.WRONG;
  }

  boolean needsReview(final Instant now) {
    if (this.isUnfamiliar()) {
      return true;
    }

    if (this.isFamiliar() && this.lastAnswer().wasGivenMoreThan(Duration.ofDays(1), now)) {
      return true;
    }

    if (this.isMastered() && this.lastAnswer().wasGivenMoreThan(Duration.ofDays(3), now)) {
      return true;
    }

    return false;
  }

  private Answer lastAnswer() {
    return this.answers.getLast();
  }

  boolean isUnfamiliar() {
    return this.rootEntity.getType() == CardType.UNFAMILIAR;
  }

  boolean isFamiliar() {
    return this.rootEntity.getType() == CardType.FAMILIAR;
  }

  boolean isMastered() {
    return this.rootEntity.getType() == CardType.MASTERED;
  }
}
