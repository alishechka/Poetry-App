package com.boss.poetrydb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.boss.poetrydb.service.AboutFragment;
import com.example.cognito.R;

public class OptionsMenuActivity extends AppCompatActivity {
    private final String ABOUT_FRAGMENT = "AboutFragment";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment;
        switch (item.getItemId()) {
            case R.id.action_info:
                selectedFragment = new AboutFragment();
                swapFragment(selectedFragment, item.getItemId(), ABOUT_FRAGMENT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void swapFragment(Fragment fragment, int itemId, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(tag)
                    .commit();
        }
    }
}