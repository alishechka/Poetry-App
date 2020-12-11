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
import com.example.cognito.databinding.FragmentSinglePoemBinding;
import com.example.cognito.databinding.PoemLayoutBinding;

import timber.log.Timber;

import static com.example.cognito.common.Constants.POEM_TITLE;

public class DisplaySinglePoemFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentSinglePoemBinding binding;
    private PoemLayoutBinding poemBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSinglePoemBinding.inflate(inflater, container, false);
        poemBinding = PoemLayoutBinding.bind(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final String title = bundle.getString(POEM_TITLE);
            viewModel.getPoem(title);
            Timber.d("viewModel called %s", title);
            viewModel.poem().observe(this, poem -> {
                poemBinding.poemTitle.setText(poem.getTitle());
                poemBinding.poemAuthor.setText(poem.getAuthor());
                StringBuilder builder = new StringBuilder();
                for (String details : poem.getLines()) {
                    builder.append(details + "\n");
                }
                poemBinding.poemPoem.setText(builder.toString());
                poemBinding.favsToggler.setChecked(true);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
