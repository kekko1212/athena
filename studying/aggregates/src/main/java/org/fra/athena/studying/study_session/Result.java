package org.fra.athena.studying.study_session;

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
