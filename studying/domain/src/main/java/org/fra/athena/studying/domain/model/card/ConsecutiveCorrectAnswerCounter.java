package org.fra.athena.studying.domain.model.card;

class ConsecutiveCorrectAnswerCounter {
  private Integer counter;

  private ConsecutiveCorrectAnswerCounter(final Integer counter) {
    this.counter = counter;
  }

  static ConsecutiveCorrectAnswerCounter initialise() {
    return new ConsecutiveCorrectAnswerCounter(0);
  }

  void increase() {
    this.counter += 1;
  }

  void reset() {
    this.counter = 0;
  }

  Integer getValue() {
    return this.counter;
  }
}
