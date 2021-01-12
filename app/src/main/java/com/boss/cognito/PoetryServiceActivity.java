package com.boss.cognito;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.boss.cognito.service.FavouriteFragment;
import com.boss.cognito.service.RandomFragment;
import com.boss.cognito.service.SearchFragment;
import com.example.cognito.R;
import com.example.cognito.databinding.ActivityPoetryServiceBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PoetryServiceActivity extends OptionsMenuActivity {
    private RandomFragment randomFragment;
    private final String RANDOM_FRAGMENT = "myRandomFragmentTag";
    private ActivityPoetryServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPoetryServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appbar.toolBar.setTitle(R.string.app_bar_title);
        setSupportActionBar(binding.appbar.toolBar);

        if (savedInstanceState != null) {
            randomFragment = (RandomFragment) getSupportFragmentManager().findFragmentByTag(RANDOM_FRAGMENT);

        } else if (randomFragment == null) {
            randomFragment = new RandomFragment();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.nav_random);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_random:
//                        swapFragment(randomFragment, item.getItemId(), RANDOM_FRAGMENT);
                        selectedFragment = randomFragment;
                        break;
                    case R.id.nav_favourites:
                        selectedFragment = new FavouriteFragment();
                        break;
                    case R.id.nav_search:
                        selectedFragment = new SearchFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }
                return true;
            };

    private void swapFragment(Fragment fragment, int itemId, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, tag)
                    .commit();
        }
    }

}



