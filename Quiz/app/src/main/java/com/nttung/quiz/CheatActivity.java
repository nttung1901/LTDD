package com.nttung.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER = "thanh-tung nguyen.quiz.answer";
    public static final String EXTRA_ANSWER_RESULT = "thanh-tung nguyen.quiz.answer_result";
    private boolean mAnswer;
    private Button mButtonShowAnswer;
    private TextView mTexViewAnswer;

    public static Intent newIntent(Context pakageContext, boolean answer){
        Intent i = new Intent(pakageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, answer);
        return i;
    }

    private void setAnswerResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_RESULT, isAnswerShow);
        setResult(RESULT_OK, data);
    }

    public static boolean getAnswerShow(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        mTexViewAnswer = findViewById(R.id.textview_answer);
        mButtonShowAnswer= findViewById(R.id.button_show_answer);
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTexViewAnswer.setText(String.valueOf(mAnswer));
                setAnswerResult(true);
            }
        });

    }
}