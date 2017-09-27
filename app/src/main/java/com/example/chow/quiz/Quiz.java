package com.example.chow.quiz;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by per6 on 9/15/17.
 */

public class Quiz implements Parcelable {

    private int currentQuestionLocation, score;
    private boolean answer;
    private ArrayList<Question> questions;
    private int backgroundColor;
    private boolean ansChosen;

    public Quiz(ArrayList<Question> questions) {
        currentQuestionLocation = -1;
        ansChosen = false;
        this.questions = questions;
    }

    public Question nextQuestion()
    {
        ansChosen = false;
        backgroundColor = Color.WHITE;
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

    public boolean checkAnsForCurrentQuestion(boolean ans)
    {
        answer = ans;
        return questions.get(currentQuestionLocation).checkAns(ans);
    }

    public void updateScore(boolean ans)
    {
        ansChosen = true;
        if (checkAnsForCurrentQuestion(ans))
        {
            score++;
            backgroundColor = Color.GREEN;
        }
        else
        {
            backgroundColor = Color.RED;
        }
    }

    public int getScore() {
        return score;
    }

    public int getColor() {
        return backgroundColor;
    }

    public int getCurrentQuestionLocation() {
        return currentQuestionLocation;
    }

    public void setCurrentQuestionLocation(int currentQuestionLocation)
    {
        this.currentQuestionLocation = currentQuestionLocation;
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected Quiz(Parcel in) {
        answer = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            questions = new ArrayList<Question>();
            in.readList(questions, Question.class.getClassLoader());
        } else {
            questions = null;
        }
        backgroundColor = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (answer ? 0x01 : 0x00));
        if (questions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(questions);
        }
        dest.writeInt(backgroundColor);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getNumQuestions()
    {
        return questions.size();
    }

    public boolean isAnsChosen() {
        return ansChosen;
    }
}