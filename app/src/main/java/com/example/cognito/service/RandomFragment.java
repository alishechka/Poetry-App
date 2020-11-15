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

import java.io.Serializable;

import timber.log.Timber;

public class RandomFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentRandomBinding binding;
    private PoemLayoutBinding poemBinding;
    private PoemModel savedTempModel;

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

//        Timber.d("XXXXXXXXXXX %s", savedTempModel.getTitle());
//
//        if (savedTempModel!=null){
//            poemBinding.randomTitle.setText(savedTempModel.getTitle());
//            poemBinding.randomAuthor.setText(savedTempModel.getAuthor());
//            StringBuilder builder = new StringBuilder();
//            for (String details : savedTempModel.getLines()) {
//                builder.append(details + "\n");
//            }
//            poemBinding.randomPoem.setText(builder.toString());
//        }

        binding.randomRefresh.setOnRefreshListener(() -> {
            viewModel.getPoemData();
            binding.randomRefresh.setRefreshing(false);
        });

        viewModel.poem().observe(this, randomPoem -> {
//            savedTempModel=randomPoem;
//            Timber.d("XXXXXXXXXXX %s", savedTempModel.getTitle());
            poemBinding.randomTitle.setText(randomPoem.getTitle());
            poemBinding.randomAuthor.setText(randomPoem.getAuthor());
            StringBuilder builder = new StringBuilder();
            for (String details : randomPoem.getLines()) {
                builder.append(details + "\n");
            }
            poemBinding.randomPoem.setText(builder.toString());
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

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("model", (Serializable) savedTempModel);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (savedInstanceState != null) {
//            savedTempModel = (PoemModel) savedInstanceState.getSerializable("model");
//            Timber.d(savedTempModel.getTitle());
//
//        }
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
