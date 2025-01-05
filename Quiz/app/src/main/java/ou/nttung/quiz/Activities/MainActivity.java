package ou.nttung.quiz.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ou.nttung.quiz.Models.Question;
import ou.nttung.quiz.R;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private static final String KEY_CHEATER = "cheater";

    private TextView mQuestion;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPreviousButton;
    private Button mNextButton;
    private ImageButton mPreviousImageButton;
    private ImageButton mNextImageButton;
    private Toast toastAnswer;
    private Button mCheatButton;
    private Question[] questionBank = new Question[]{
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true),
    };

    private boolean[] isCheater = new boolean[questionBank.length];

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestion = findViewById(R.id.question);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mCheatButton = findViewById(R.id.cheat_button);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mPreviousButton = findViewById(R.id.previous_button);
            mNextButton = findViewById(R.id.next_button);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentIndex = (currentIndex + 1)%questionBank.length;
                    updateQuestion();
                }
            });

            mPreviousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentIndex = (currentIndex - 1 + mQuestion.length())%questionBank.length;
                    updateQuestion();
                }
            });
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // LandScape
            mPreviousImageButton = findViewById(R.id.previous_imageButton);
            mNextImageButton = findViewById(R.id.next_imageButton);
            mNextImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentIndex = (currentIndex + 1)%questionBank.length;
                    updateQuestion();
                }
            });

            mPreviousImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentIndex = (currentIndex - 1 + mQuestion.length())%questionBank.length;
                    updateQuestion();
                }
            });
        }


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(MainActivity.this,questionBank[currentIndex].isAnswerTrue());
                startActivityForResult.launch(intent);
            }
        });

        if(savedInstanceState!=null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            isCheater = savedInstanceState.getBooleanArray(KEY_CHEATER);
        }
        updateQuestion();
    }

    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o!= null && o.getResultCode() == RESULT_OK)
                        if(o.getData() != null)
                            isCheater[currentIndex] = CheatActivity.getAnswerShow(o.getData());
                }
            }
    );

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,currentIndex);
        outState.putBooleanArray(KEY_CHEATER,isCheater);
    }

    private void updateQuestion(){
        mQuestion.setText(questionBank[currentIndex].getTextResId());
    }

    private void checkAnswer(boolean userAnswer){
        boolean answerIsTrue = questionBank[currentIndex].isAnswerTrue();

        int messageId =0;
        if(isCheater[currentIndex])
            messageId = R.string.cheating_toast;
        else {
            if (userAnswer == answerIsTrue)
                messageId = R.string.true_toast;
            else
                messageId = R.string.false_toast;
        }
        if(toastAnswer != null){
            toastAnswer.cancel();
        }
        toastAnswer = Toast.makeText(this, messageId, Toast.LENGTH_SHORT);
        toastAnswer.show();
    }

}