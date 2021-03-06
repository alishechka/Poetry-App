package com.boss.poetrydb.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.boss.poetrydb.MainViewModel;
import com.boss.poetrydb.OnClickedListener;
import com.boss.poetrydb.adapter.FavouritesAdapter;
import com.boss.poetrydb.common.Constants;
import com.example.cognito.R;
import com.example.cognito.databinding.FragmentFavouritesBinding;

import timber.log.Timber;

public class FavouriteFragment extends Fragment implements OnClickedListener {
    private MainViewModel viewModel;
    private FragmentFavouritesBinding binding;
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
        Bundle bundle = new Bundle();
        bundle.putString(Constants.POEM_TITLE, title);
        bundle.putBoolean(Constants.IS_CHECKED, true);

        Fragment frag = new DisplaySinglePoemFragment();
        frag.setArguments(bundle);
        FragmentManager ft = getFragmentManager();
        ft.beginTransaction()
                .replace(R.id.fragment_container, frag)
                .addToBackStack("tag")
                .commit();


    }
}
