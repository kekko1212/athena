package org.fra.athena.studying.domain.model.card;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class CardAggregate {

  CardRootEntity rootEntity;
  LinkedList<Answer> answers = new LinkedList<>();

  private CardAggregate(final Term term, final CardType type) {
    this.rootEntity = new CardRootEntity(type, term);
  }

  public static CardAggregate newCard(final String term) {
    return new CardAggregate(new Term(term), CardType.UNFAMILIAR);
  }

  public Result answer(final String givenAnswer, final Clock clock) {
    final Answer answer = new Answer(givenAnswer, clock);

    this.answers.add(answer);

    if (this.rootEntity.isCorrect(answer)) {
      this.rootEntity.processCorrectAnswer();

      return Result.CORRECT;
    }

    this.rootEntity.processWrongAnswer();

    return Result.WRONG;
  }

  public boolean needsReview(final Instant now) {
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

  public boolean isUnfamiliar() {
    return this.rootEntity.getType() == CardType.UNFAMILIAR;
  }

  boolean isFamiliar() {
    return this.rootEntity.getType() == CardType.FAMILIAR;
  }

  boolean isMastered() {
    return this.rootEntity.getType() == CardType.MASTERED;
  }
}
