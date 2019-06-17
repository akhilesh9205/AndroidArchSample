package com.example.androidarchsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidarchsample.repository.Word;
import com.example.androidarchsample.repository.WordRepository;

public class NewWordActivity extends AppCompatActivity {

    private EditText mEditWordView;
    private WordRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        mRepository = WordRepository.getInstance(getApplication());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mRepository.insert(new Word(mEditWordView.getText().toString()));
                finish();
            }
        });


    }
}
