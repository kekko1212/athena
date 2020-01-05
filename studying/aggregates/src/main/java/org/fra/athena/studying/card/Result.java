package org.fra.athena.studying.card;

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
