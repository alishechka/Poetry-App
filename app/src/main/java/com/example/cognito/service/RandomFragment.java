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
import com.example.cognito.model.PoemModel;

public class RandomFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentRandomBinding binding;
    private PoemLayoutBinding poemBinding;
    private PoemModel currentPoemModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRandomBinding.inflate(inflater, container, false);
        poemBinding = PoemLayoutBinding.bind(binding.getRoot());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (currentPoemModel != null) {
            poemBinding.poemTitle.setText(currentPoemModel.getTitle());
            poemBinding.poemAuthor.setText(currentPoemModel.getAuthor());
            StringBuilder builder = new StringBuilder();
            for (String details : currentPoemModel.getLines()) {
                builder.append(details + "\n");
            }
            poemBinding.poemPoem.setText(builder.toString());
        }

        binding.randomRefresh.setOnRefreshListener(() -> {
            viewModel.getRandomPoemData();
            binding.randomRefresh.setRefreshing(false);
        });

        viewModel.poemRandom().observe(this, randomPoem -> {
            // save current model so its present when navigating back to this fragment
            currentPoemModel = randomPoem;

            poemBinding.poemTitle.setText(randomPoem.getTitle());
            poemBinding.poemAuthor.setText(randomPoem.getAuthor());
            StringBuilder builder = new StringBuilder();
            for (String details : randomPoem.getLines()) {
                builder.append(details + "\n");
            }
            poemBinding.poemPoem.setText(builder.toString());
            //add to favs
            poemBinding.addToFavs.setOnClickListener(v -> {
                        viewModel.addToFavourites(randomPoem.getTitle());
                    }
            );
            viewModel.addToRoomDB(randomPoem);
            poemBinding.addToRoomdb.setOnClickListener(v -> {
                viewModel.getPoemByTitle(randomPoem.getTitle());
            });
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
