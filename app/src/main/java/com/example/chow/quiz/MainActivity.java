package com.example.chow.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SCORE = "Score";
    private static final String CURRENT_QUIZ = "current quiz before resume";
    private TextView questionText;
    private Quiz quiz;
    private Button trueAns, falseAns, next;
    private ConstraintLayout background;
    
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText = (TextView) findViewById(R.id.question_view);
        trueAns = (Button) findViewById(R.id.ans_true);
        falseAns = (Button) findViewById(R.id.ans_false);
        next = (Button) findViewById(R.id.button_next);
        background = (ConstraintLayout) findViewById(R.id.layout_main_background);
        playAgain();
        //check if we're resuming from a previous state
        if (savedInstanceState != null)
        {
            Parcelable currentQuiz = savedInstanceState.getParcelable(CURRENT_QUIZ);
            quiz = (Quiz) currentQuiz;
            updateProgress();
        }
    }

    private void updateProgress() {
        if (quiz.isAnsChosen())
        {
            onAnsClick();
        }
    }

    public void trueClicked(View view) {
        quiz.updateScore(true);
        onAnsClick();
    }

    public void falseClicked(View view) {
        quiz.updateScore(false);
        onAnsClick();
    }

    public void onAnsClick()
    {
        trueAns.setEnabled(false);
        falseAns.setEnabled(false);
        background.setBackgroundColor(quiz.getColor());
        next.setEnabled(true);
    }

    public void displayNextQuestion()
    {
        Question currentQuestion = quiz.nextQuestion();
        if (currentQuestion == null)
        {
            Intent i = new Intent(this, EndActivity.class);
            i.putExtra(SCORE, "" + quiz.getScore());
            startActivity(i);
            playAgain();
            /*
            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
            trueAns.setVisibility(View.INVISIBLE);
            falseAns.setVisibility(View.INVISIBLE);
            questionText.setText("Score: " + quiz.getScore());
            */
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
        background.setBackgroundColor(quiz.getColor());
    }

    public void playAgain() {
        makeQuizAndQuestions();
        displayNextQuestion();
        updateProgress();
        next.setVisibility(View.VISIBLE);
        trueAns.setVisibility(View.VISIBLE);
        falseAns.setVisibility(View.VISIBLE);
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

    //To prevent resetting during rotation
    //use a savedInstanceState

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: method fired");
        outState.putParcelable(CURRENT_QUIZ, quiz);
    }

    //Android Lifecycle Methods
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: method fired");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: method fired");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: method fired");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: method fired");
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume: method fired");
    }
}
