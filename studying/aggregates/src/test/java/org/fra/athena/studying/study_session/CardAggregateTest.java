package org.fra.athena.studying.study_session;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardAggregateTest {

  private Clock clock;

  @BeforeEach
  void beforeEach() {
    this.clock = Clock.fixed(Instant.parse("2020-01-01T00:00:00.00Z"), ZoneId.systemDefault());
  }

  @Test
  void correctResultIsReturnedIfCorrectAnswerIsGiven() {
    final CardAggregate cardAggregate = CardAggregate.newCard(new Term("Husband"));
    final Answer correctAnswer = new Answer("Husband", this.clock);

    final Result result = cardAggregate.answer(correctAnswer);

    Assertions.assertTrue(result.isCorrect());
  }

  @Test
  void wrongResultIsReturnedIfWrongAnswerIsGiven() {
    final CardAggregate cardAggregate = CardAggregate.newCard(new Term("Woman"));
    final Answer wrongAnswer = new Answer("Man", this.clock);

    final Result result = cardAggregate.answer(wrongAnswer);

    Assertions.assertFalse(result.isCorrect());
  }

  @Test
  void cardIsUnfamiliarByDefault() {
    final CardAggregate cardAggregate = CardAggregate.newCard(new Term("House"));

    Assertions.assertTrue(cardAggregate.isUnfamiliar());
  }

  @Test
  void cardIsPromotedToFamiliarIfAnsweredCorrectly() {
    final CardAggregate familiarCard = this.makeFamiliarCard();

    Assertions.assertTrue(familiarCard.isFamiliar());
  }

  @Test
  void cardIsStillFamiliarIfAnsweredCorrectlyASecondTimeInARow() {
    final CardAggregate familiarCard = this.makeFamiliarCard();
    familiarCard.answer(new Answer("House", this.clock));

    Assertions.assertTrue(familiarCard.isFamiliar());
  }

  @Test
  void cardBecomeMasteredIfAnsweredCorrectlyThreeOrMoreTimesInARow() {
    final CardAggregate masteredCard = this.makeMasteredCard();

    Assertions.assertTrue(masteredCard.isMastered());
  }

  @Test
  void cardBecomeUnfamiliarIfAnsweredWrongly() {
    final CardAggregate masteredCard = this.makeMasteredCard();
    masteredCard.answer(new Answer("Wrong Answer", this.clock));

    Assertions.assertTrue(masteredCard.isUnfamiliar());
  }

  @Test
  void cardNeedsStudyIfFamiliarAndMoreThanOneDayPassedFromLastCorrectAnswer() {
    final Instant oneDayAfterFixedDate = Instant.now(this.clock).plus(1, ChronoUnit.DAYS);

    final CardAggregate cardAggregate = this.makeFamiliarCard();

    Assertions.assertTrue(cardAggregate.needsReview(oneDayAfterFixedDate));
  }

  @Test
  void cardNeedsStudyIfMasteredAndMoreThanThreeDaysPassedFromLastCorrectAnswer() {
    final Instant threeDaysAfterFixedDate = Instant.now(this.clock).plus(3, ChronoUnit.DAYS);

    final CardAggregate masteredCard = this.makeMasteredCard();

    Assertions.assertTrue(masteredCard.needsReview(threeDaysAfterFixedDate));
  }

  private CardAggregate makeMasteredCard() {
    final CardAggregate cardAggregate = CardAggregate.newCard(new Term("House"));
    cardAggregate.answer(new Answer("House", this.clock));
    cardAggregate.answer(new Answer("House", this.clock));
    cardAggregate.answer(new Answer("House", this.clock));

    return cardAggregate;
  }

  private CardAggregate makeFamiliarCard() {
    final CardAggregate cardAggregate = CardAggregate.newCard(new Term("House"));
    cardAggregate.answer(new Answer("House", this.clock));

    return cardAggregate;
  }
}
