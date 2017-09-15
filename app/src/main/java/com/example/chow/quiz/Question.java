package com.example.chow.quiz;

/**
 * Created by per6 on 9/15/17.
 */

public class Question {

    private String questionText;
    private boolean ans;

    public Question(String questionText, boolean ans) {
        this.questionText = questionText;
        this.ans = ans;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean checkAns(boolean chosenAns)
    {
        return chosenAns == ans;
    }
}
