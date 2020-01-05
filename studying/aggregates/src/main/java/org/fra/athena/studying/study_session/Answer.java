package org.fra.athena.studying.study_session;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class Answer {
  private final String value;
  private final Instant createdAt;

  Answer(final String value, final Clock clock) {
    this.value = value;
    this.createdAt = Instant.now(clock);
  }

  boolean wasGivenMoreThan(final Duration duration, final Instant now) {
    final Instant actualDate = this.createdAt.plus(duration.toDays(), ChronoUnit.DAYS);

    return actualDate.isBefore(now) || actualDate.equals(now);
  }

  String getValue() {
    return this.value;
  }
}
