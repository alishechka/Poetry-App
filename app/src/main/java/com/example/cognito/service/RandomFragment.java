package com.example.cognito.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cognito.MainViewModel;
import com.example.cognito.R;
import com.example.cognito.databinding.FragmentRandomBinding;
import com.example.cognito.databinding.PoemLayoutBinding;
import com.example.cognito.model.PoemModel;

import timber.log.Timber;

public class RandomFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentRandomBinding binding;
    private PoemLayoutBinding poemBinding;
    private PoemModel currentPoemModel;
    private boolean faved;

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

        //to display previously loaded PoemModel
        if (currentPoemModel != null) {
            setPoemViews(currentPoemModel);
            poemBinding.favsToggler.setChecked(faved);
            manageToggleDrawableState(faved);
            Timber.d("if not equal null %s", faved);

//            viewModel.getFavourites();
//            viewModel.favourites().observe(this, favs -> {
//                for (String title : favs.getFavourites()) {
//                    if (currentPoemModel.getTitle().equals(title)) {
//                        poemBinding.favsToggler.setChecked(true);
//                    }
//                }
//            });
        }

        binding.randomRefresh.setOnRefreshListener(() -> {
            viewModel.getRandomPoemData();
            binding.randomRefresh.setRefreshing(false);
        });

        viewModel.poemRandom().observe(this, randomPoem -> {
            // save current model so its present when navigating back to this fragment
            currentPoemModel = randomPoem;
            setPoemViews(randomPoem);

            poemBinding.favsToggler.setChecked(false);

//          check if current Poem has been faved before
            viewModel.getFavourites();
            viewModel.favourites().observe(this, favs -> {
                for (String title : favs.getFavourites()) {
                    if (randomPoem.getTitle().equals(title)) {
                        poemBinding.favsToggler.setChecked(true);
                    } else {
                        poemBinding.favsToggler.setChecked(false);
                    }
                }
            });

        });

        poemBinding.favsToggler.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    Timber.d("on clicked toggler %s", isChecked);
                    faved = isChecked;
                    manageToggleDrawableState(isChecked);
                    if (isChecked) {
                        viewModel.addToFavourites(currentPoemModel.getTitle());
                    } else {
                        viewModel.removeFavourite(currentPoemModel.getTitle());
                    }
                }
        );
    }

    private void setPoemViews(PoemModel randomPoem) {
        poemBinding.poemTitle.setText(randomPoem.getTitle());
        poemBinding.poemAuthor.setText(randomPoem.getAuthor());
        StringBuilder builder = new StringBuilder();
        for (String details : randomPoem.getLines()) {
            builder.append(details + "\n");
        }
        poemBinding.poemPoem.setText(builder.toString());
    }

    public void manageToggleDrawableState(boolean checked) {
        if (checked) {
            poemBinding.favsToggler.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fav_on));
        } else {
            poemBinding.favsToggler.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fav_off));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
