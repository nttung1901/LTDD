package com.nttung.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonNext;
    private Button mButtonPrevious;
    private TextView mTextViewQuestion;
    private Button mButtonCheat;

    private Question[] mQuestionsBank= {
            new Question(R.string.question1,true),
            new Question(R.string.question2,false),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true),
            new Question(R.string.question5,true),
    };

    public void updateQuestion(){
        mTextViewQuestion.setText(mQuestionsBank[mCurrentIndex].getmTestResId());
    }

    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mTextViewQuestion= findViewById(R.id.text_view_question);
        updateQuestion();
        mButtonTrue = findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionsBank[mCurrentIndex].ismAnswerTrue())
                    Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();
            }
        });

        mButtonFalse = findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mQuestionsBank[mCurrentIndex].ismAnswerTrue())
                    Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();
            }
        });

        mButtonCheat = findViewById(R.id.button_cheat);
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer = mQuestionsBank[mCurrentIndex].ismAnswerTrue();
                Intent i = CheatActivity.newIntent(MainActivity.this,answer);
                startActivity(i);
            }
        });

        mButtonNext= findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1)% mQuestionsBank.length;
                updateQuestion();
            }
        });

        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex >0)
                    mCurrentIndex = mCurrentIndex - 1;
                updateQuestion();
            }
        });
    }
}