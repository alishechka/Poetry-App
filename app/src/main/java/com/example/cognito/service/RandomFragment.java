package com.example.cognito.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cognito.MainViewModel;
import com.example.cognito.databinding.FragmentRandomBinding;
import com.example.cognito.databinding.PoemLayoutBinding;

public class RandomFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentRandomBinding binding;
    private PoemLayoutBinding poemBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRandomBinding.inflate(inflater, container, false);
        poemBinding=PoemLayoutBinding.bind(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        binding.randomRefresh.setOnRefreshListener(() -> {
            viewModel.getPoemData();
            binding.randomRefresh.setRefreshing(false);
        });

        viewModel.poem().observe(this, randomPoem -> {
            poemBinding.randomTitle.setText(randomPoem.getTitle());
            poemBinding.randomAuthor.setText(randomPoem.getAuthor());
            StringBuilder builder = new StringBuilder();
            for (String details : randomPoem.getLines()) {
                builder.append(details + "\n");
            }
            poemBinding.randomPoem.setText(builder.toString());
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
