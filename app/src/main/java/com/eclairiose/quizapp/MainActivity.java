package com.eclairiose.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eclairiose.quizapp.Model.Questions;
import com.eclairiose.quizapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private int currentIndex=0;
private Questions[] questions = new Questions[]{
                    new Questions(R.string.question_amendments,false),
                    new Questions(R.string.question_constitution,true),
                    new Questions(R.string.question_declaration,false),
                    new Questions(R.string.question_government,true),
                    new Questions(R.string.question_religion,false),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

binding = DataBindingUtil.setContentView(this,R.layout.activity_main  );
binding.textView.setText(questions[currentIndex].getAnswerResId());

binding.button4.setOnClickListener(v -> {
    Log.e("Main", "onCreate: " +currentIndex++);

    currentIndex = (currentIndex+1) % questions.length;
    updateQuestion();
});

binding.button3.setOnClickListener(v -> {

if(currentIndex > 0){
    currentIndex = (currentIndex-1) % questions.length;
    updateQuestion();
}

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            binding.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

        });

    }

    private void updateQuestion() {
        binding.textView.setText(questions[currentIndex].getAnswerResId());
    }


    public void checkAnswer(Boolean userChooseCorrect){
Boolean answerIsCorrect = questions[currentIndex].getAnswerTrue();
int messageId;

if(answerIsCorrect==userChooseCorrect){
    messageId = R.string.correct_answer;
}else {
    messageId = R.string.wrong_answer;
}

        Snackbar.make(binding.imageView,messageId, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}