package ou.nttung.quiz.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ou.nttung.quiz.R;

public class CheatActivity extends AppCompatActivity {
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_ANSWER_RESULT= "answer_result";
    private static final String KEY_CHEATER = "cheater";

    private TextView mAnswer;
    private Button mShowAnswerButton;
    private Boolean answer;
    private boolean cheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswer = findViewById(R.id.answer);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        answer = getIntent().getBooleanExtra(KEY_ANSWER,false);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswer.setText(answer.toString());
                setAnswerResult(true);
                cheater =true;
            }
        });

        if(savedInstanceState!=null) {
            if (savedInstanceState.getBoolean(KEY_CHEATER, false))
                mShowAnswerButton.callOnClick();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CHEATER, cheater);
    }

    public static Intent newIntent(Context context, boolean answer){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(KEY_ANSWER, answer);
        return intent;
    }

    private void setAnswerResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(KEY_ANSWER_RESULT,isAnswerShow);
        setResult(RESULT_OK,data);
    }

    public static boolean getAnswerShow(Intent intent){
        return intent.getBooleanExtra(KEY_ANSWER_RESULT,false);
    }
}