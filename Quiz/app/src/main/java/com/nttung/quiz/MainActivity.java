package com.nttung.quiz;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private TextView mTextViewQuesTion;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonPrevious;
    private Button mButtonNext;

    private int mCurrentIndex = 0;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true)
    };

    private void updateQuestion(){
        mTextViewQuesTion.setText(mQuestionBank[mCurrentIndex].getmTextResId());
    }

    private void checkAnswer(boolean userPressTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageId =0;
        if(userPressTrue == answerIsTrue)
            messageId = R.string.toast_true;
        else
            messageId = R.string.toast_false;
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        mTextViewQuesTion = findViewById(R.id.textview_question);
        updateQuestion();

        mButtonTrue = findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mButtonFalse = findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex>0) {
                    mCurrentIndex--;
                    updateQuestion();
                }
            }
        });

        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(KEY_INDEX, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }
}