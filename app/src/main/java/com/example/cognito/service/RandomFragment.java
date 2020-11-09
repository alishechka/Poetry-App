package com.example.cognito.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cognito.MainViewModel;
import com.example.cognito.R;

public class RandomFragment extends Fragment {
    private MainViewModel viewModel;
    private TextView title, author, poem;
    private Button refreshButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);
        title = view.findViewById(R.id.random_title);
        author = view.findViewById(R.id.random_author);
        poem = view.findViewById(R.id.random_poem);
        refreshButton = view.findViewById(R.id.random_button_refresh);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        refreshButton.setOnClickListener(v -> {
            viewModel.getPoemData();
        });

        viewModel.poem().observe(this, randomPoem -> {
            title.setText(randomPoem.getTitle());
            author.setText(randomPoem.getAuthor());

            StringBuilder builder = new StringBuilder();
            for (String details : randomPoem.getLines()) {
                builder.append(details + "\n");
            }
            poem.setText(builder.toString());

        });

    }
}
