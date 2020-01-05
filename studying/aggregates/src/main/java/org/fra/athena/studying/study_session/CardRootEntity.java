package org.fra.athena.studying.study_session;

public class CardRootEntity {

  private final Term term;
  private CardType type;
  private ConsecutiveCorrectAnswerCounter consecutiveCorrectAnswerCounter;

  CardRootEntity(final CardType type, final Term term) {
    this.type = type;
    this.term = term;
    this.consecutiveCorrectAnswerCounter = ConsecutiveCorrectAnswerCounter.initialise();
  }

  CardType getType() {
    return this.type;
  }

  Term getTerm() {
    return this.term;
  }

  boolean isCorrect(final Answer answer) {
    return this.getTerm().getValue().equals(answer.getValue());
  }

  void processCorrectAnswer() {
    this.consecutiveCorrectAnswerCounter.increase();
    this.promote();
  }

  private void promote() {
    if (this.consecutiveCorrectAnswerCounter.getValue() <= 2) {
      this.promoteCardToFamiliar();

      return;
    }

    if (this.consecutiveCorrectAnswerCounter.getValue() >= 3) {
      this.promoteCardToMastered();
    }
  }

  private void demote() {
    this.type = CardType.UNFAMILIAR;
  }

  private void promoteCardToFamiliar() {
    this.type = CardType.FAMILIAR;
  }

  private void promoteCardToMastered() {
    this.type = CardType.MASTERED;
  }

  void processWrongAnswer() {
    this.consecutiveCorrectAnswerCounter.reset();
    this.demote();
  }
}
