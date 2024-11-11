package com.nttung.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.internal.EdgeToEdgeUtils;

public class CheatActivity extends AppCompatActivity {

    private boolean mAnswer;
    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;
    private static final String EXTRA_ANSWER_RESULT= "nttung.quiz.answer_result";
    private static final String EXTRA_ANSWER = "nttung.quiz.answer";


    public static Intent newIntent(Context pakageContext, boolean answer){
        Intent i = new Intent(pakageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER,answer);
        return i;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        mTextViewAnswer = findViewById(R.id.text_view_answer);

        mTextViewAnswer = findViewById(R.id.text_view_answer);
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer)
                    mTextViewAnswer.setText("True");
                else
                    mTextViewAnswer.setText("False");
                setAnswerResult(true);
            }
        });
    }
    private void setAnswerResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_RESULT, isAnswerShow);
        setResult(RESULT_OK, data);
    }

    public static boolean getAnswerShow(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT,false);
    }
}