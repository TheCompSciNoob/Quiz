package com.example.chow.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        TextView score = (TextView) findViewById(R.id.score_view);
        score.setText(getIntent().getStringExtra(MainActivity.SCORE));
        Intent i = getIntent();
    }

    public void exit(View view) {
        finish();
    }
}
