package com.example.chow.quiz;

import java.util.ArrayList;

/**
 * Created by per6 on 9/15/17.
 */

public class Quiz {

    private int currentQuestionLocation;
    private ArrayList<Question> questions;

    public Quiz(ArrayList<Question> questions) {
        currentQuestionLocation = -1;
        this.questions = questions;
    }

    public Question nextQuestion()
    {
        currentQuestionLocation++;
        if (currentQuestionLocation < questions.size())
        {
            return questions.get(currentQuestionLocation);
        }
        else
        {
            return null;
        }
    }
}
