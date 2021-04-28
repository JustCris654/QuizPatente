package com.justcris.QuizPatente;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Quiz {
    private ArrayList<QuestionQuiz> _quiz;
    //    private boolean _renewal;
    private AccessDatabase _db;
    private ArrayList<Boolean> _answers;

    public Quiz() {
//        this._renewal = _renewal;
        _quiz = new ArrayList<>();
        _db = new AccessDatabase();
        _answers = new ArrayList<>();
    }

    public boolean PopulateQuiz() {
        ArrayList<QuestionQuiz> quizs = _db.GetQuiz();
        if (quizs == null) return false;
        _quiz = quizs;
        return true;
    }

    public ArrayList<Integer> GenerateQuiz(boolean _renewal) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        ArrayList<Integer> nums = new ArrayList<>(list.subList(0, _renewal ? 4 : 9));
        return nums;
    }

    public QuestionQuiz GetQuestionQuiz(int i) {
        return _quiz.get(i);
    }

    public boolean GetAnswer(int i) {
        return _answers.get(i);
    }
}
