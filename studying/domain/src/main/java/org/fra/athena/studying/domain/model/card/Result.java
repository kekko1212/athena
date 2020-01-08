package org.fra.athena.studying.domain.model.card;

enum Result {
  CORRECT {
    @Override
    boolean isCorrect() {
      return true;
    }
  },
  WRONG {
    @Override
    boolean isCorrect() {
      return false;
    }
  };

  abstract boolean isCorrect();
}
