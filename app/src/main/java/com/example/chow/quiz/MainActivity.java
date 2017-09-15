package com.example.chow.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private Quiz quiz;
    private Question currentQuestion;
    private Button trueAns, falseAns, next, playAgain;
    private int score;
    private ConstraintLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText = (TextView) findViewById(R.id.question_view);
        trueAns = (Button) findViewById(R.id.ans_true);
        falseAns = (Button) findViewById(R.id.ans_false);
        next = (Button) findViewById(R.id.button_next);
        playAgain = (Button) findViewById(R.id.again_play);
        background = (ConstraintLayout) findViewById(R.id.layout_main_background);
        makeQuizAndQuestions();
        displayNextQuestion();
    }

    public void trueClicked(View view) {
        trueAns.setEnabled(false);
        falseAns.setEnabled(false);
        if (currentQuestion.checkAns(true))
        {
            score++;
            background.setBackgroundColor(Color.GREEN);
        }
        else
        {
            background.setBackgroundColor(Color.RED);
        }
        next.setEnabled(true);
    }

    public void falseClicked(View view) {
        trueAns.setEnabled(false);
        falseAns.setEnabled(false);
        if (currentQuestion.checkAns(false))
        {
            score++;
            background.setBackgroundColor(Color.GREEN);
        }
        else
        {
            background.setBackgroundColor(Color.RED);
        }
        next.setEnabled(true);
    }

    public void displayNextQuestion()
    {
        currentQuestion = quiz.nextQuestion();
        if (currentQuestion == null)
        {
            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
            trueAns.setVisibility(View.INVISIBLE);
            falseAns.setVisibility(View.INVISIBLE);
            questionText.setText("Score: " + score);
            playAgain.setVisibility(View.VISIBLE);
        }
        else {
            questionText.setText(currentQuestion.getQuestionText());
            trueAns.setEnabled(true);
            falseAns.setEnabled(true);
            next.setEnabled(false);
        }
    }

    public void nextQuestion(View view) {
        displayNextQuestion();
        background.setBackgroundColor(Color.WHITE);
    }

    public void playAgain(View view) {
        makeQuizAndQuestions();
        displayNextQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        next.setVisibility(View.VISIBLE);
        trueAns.setVisibility(View.VISIBLE);
        falseAns.setVisibility(View.VISIBLE);
        score = 0;
    }

    private void makeQuizAndQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        //add questions here
        questions.add(new Question(getString(R.string.beard_question), true));
        questions.add(new Question(getString(R.string.kenneth_question), true));
        questions.add(new Question(getString(R.string.flat_earth_question), false));
        questions.add(new Question(getString(R.string.russian_reversal_question), true));
        quiz = new Quiz(questions);
    }
}
