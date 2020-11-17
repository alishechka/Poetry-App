package com.example.cognito.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cognito.FavouritesAdapter;
import com.example.cognito.MainViewModel;
import com.example.cognito.OnClickedListener;
import com.example.cognito.databinding.FragmentFavouritesBinding;
import com.example.cognito.model.Favourites;

import timber.log.Timber;

public class FavouriteFragment extends Fragment implements OnClickedListener {
    private MainViewModel viewModel;
    private FragmentFavouritesBinding binding;
    private Favourites currentFavourites;
    private FavouritesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getFavourites();
        viewModel.favourites().observe(this, favs -> {
            binding.favouritesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            adapter = new FavouritesAdapter(favs.getFavourites(), this);
            binding.favouritesRecyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(String title) {
        Timber.d(title);
    }
}
