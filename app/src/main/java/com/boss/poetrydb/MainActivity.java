package com.boss.poetrydb;

import android.content.Intent;
import android.os.Bundle;

import com.boss.poetrydb.login.SignInActivity;
import com.boss.poetrydb.login.SignUpActivity;
import com.example.cognito.databinding.ActivityMainBinding;

public class MainActivity extends OptionsMenuActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonMainLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });
        binding.buttonMainSignup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}
