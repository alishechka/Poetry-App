package com.boss.cognito.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.boss.cognito.MainViewModel;
import com.boss.cognito.common.Constants;
import com.boss.cognito.model.PoemModel;
import com.example.cognito.R;
import com.example.cognito.databinding.FragmentSinglePoemBinding;
import com.example.cognito.databinding.PoemLayoutBinding;

import timber.log.Timber;

public class DisplaySinglePoemFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentSinglePoemBinding binding;
    private PoemLayoutBinding poemBinding;
    private PoemModel currentPoemModel;

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
            final String title = bundle.getString(Constants.POEM_TITLE);
            final boolean faved = bundle.getBoolean(Constants.IS_CHECKED);

            viewModel.getPoem(title);
            Timber.d("viewModel called %s", title);
            viewModel.poem().observe(this, poem -> {
                currentPoemModel = poem;
                poemBinding.poemTitle.setText(poem.getTitle());
                poemBinding.poemAuthor.setText(poem.getAuthor());
                StringBuilder builder = new StringBuilder();
                for (String details : poem.getLines()) {
                    builder.append(details + "\n");
                }
                poemBinding.poemPoem.setText(builder.toString());
                poemBinding.favsToggler.setChecked(faved);

            });
            poemBinding.favsToggler.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {
                        Timber.d("on clicked toggler %s", isChecked);
                        if (isChecked) {
                            viewModel.addToFavourites(currentPoemModel.getTitle());
                            buttonView.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fav_on));
                        } else {
                            viewModel.removeFavourite(currentPoemModel.getTitle());
                            buttonView.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fav_off));
                        }
                    }
            );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
