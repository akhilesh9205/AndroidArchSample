package com.example.androidarchsample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private Application mApplication;
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    private WordRepository(Application application) {
        this.mApplication = application;
        mWordDao = WordRoomDatabase.getDatabase(mApplication).wordDao();
        mAllWords = mWordDao.getWordList();
    }

    private static WordRepository INSTANCE;

    public static WordRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new WordRepository(application);
        }
        return INSTANCE;
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
