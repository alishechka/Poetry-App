package com.boss.poetrydb.service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cognito.R;
import com.example.cognito.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    FragmentAboutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.aboutRandom.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_random_24,0,0,0);
        binding.aboutFavourites.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_star_24,0,0,0);
        binding.aboutSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_search_24,0,0,0);

    }
}