package com.example.cognito;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cognito.databinding.ActivityPoetryServiceBinding;
import com.example.cognito.service.FavouriteFragment;
import com.example.cognito.service.RandomFragment;
import com.example.cognito.service.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PoetryServiceActivity extends AppCompatActivity {
    private RandomFragment randomFragment;
    private final String RANDOM_FRAGMENT = "myRandomFragmentTag";
    private ActivityPoetryServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPoetryServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appbar.toolBar.setTitle("PoetryApp");
        setSupportActionBar(binding.appbar.toolBar);
//        if (savedInstanceState == null) {
//            bottomNavigationView.setSelectedItemId(R.id.nav_random);
//        }
        if (savedInstanceState != null) {
            randomFragment = (RandomFragment) getSupportFragmentManager().findFragmentByTag(RANDOM_FRAGMENT);
//            if (randomFragment == null) {
//                randomFragment = new RandomFragment();
//            }

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
                        swapFragment(randomFragment, item.getItemId(), RANDOM_FRAGMENT);
////                        selectedFragment = new RandomFragment();
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .add(R.id.fragment_container, randomFragment, RANDOM_FRAGMENT)
//                                .commit();
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



