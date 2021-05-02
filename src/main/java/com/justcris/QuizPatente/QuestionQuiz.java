package com.justcris.QuizPatente;

public class QuestionQuiz {
    private String _question;
    private boolean _answer;

    public QuestionQuiz(String _question, boolean _answer) {
        this._question = _question;
        this._answer = _answer;
    }

    public QuestionQuiz() {
    }

    public String get_question() {
        return _question;
    }

    public void set_question(String _question) {
        this._question = _question;
    }

    public boolean get_answer() {
        return _answer;
    }

    public void set_answer(boolean _answer) {
        this._answer = _answer;
    }
}
