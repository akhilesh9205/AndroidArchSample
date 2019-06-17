package com.example.androidarchsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidarchsample.databinding.ItemBinding;
import com.example.androidarchsample.repository.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final ItemBinding binding;

        private WordViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Word word){
            binding.setWord(word);
            binding.executePendingBindings();
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(mInflater, parent, false);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word word = mWords.get(position);
        holder.bind(word);
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}