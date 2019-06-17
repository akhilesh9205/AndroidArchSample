package com.example.androidarchsample;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.androidarchsample.repository.Word;
import com.example.androidarchsample.repository.WordRepository;

import java.util.List;
import java.util.Random;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;
    public LiveData<String> mWordAsString;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = WordRepository.getInstance(application);
        mAllWords = mRepository.getAllWords();

        mWordAsString = Transformations.map(mAllWords, new Function<List<Word>, String>() {
            @Override
            public String apply(List<Word> input) {
                return TextUtils.join("\n", input);
            }
        });
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        mRepository.insert(word);
    }

    public void addRandom() {
        mRepository.insert(new Word(randomText()));
    }

    public String randomText() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = 6;
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
