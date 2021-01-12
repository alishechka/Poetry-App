package com.example.cognito;

import android.content.Intent;
import android.os.Bundle;

import com.example.cognito.databinding.ActivityMainBinding;
import com.example.cognito.login.SignInActivity;
import com.example.cognito.login.SignUpActivity;

public class MainActivity extends OptionsMenuActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.appbar.toolBar.setTitle(R.string.app_bar_title);
//        setSupportActionBar(binding.appbar.toolBar);

        binding.buttonMainLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });
        binding.buttonMainSignup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

//        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        buttonMain.setOnClickListener(v ->
//                viewModel.getPoemData()
//        );
//
//        viewModel.poem().observe(this, randomPoem -> {
//            textMain.setText(randomPoem.getAuthor());
//            Log.i(TAG, "SUCCESS: " + randomPoem);
//        });
//        viewModel.error().observe(this, error -> {
//            textMain.setText(error);
//            Log.i(TAG, "ERROR: " + error);
//        });
//        logout();
    }

//    public void logout() {
////        sCredProvider = new CognitoCachingCredentialsProvider();
////        CognitoSettings user2 = new CognitoSettings(getApplicationContext());
////        CognitoUser cognitoUser2 = user2.getUserPool().getUser("cognitochan");
////        sCredProvider=new CognitoCachingCredentialsProvider(getApplicationContext(),cognitoUser2.getUserId(), Regions.EU_WEST_2);
////        Log.d(TAG, "onCreate: "+sCredProvider.getIdentityId());
//
////        Log.i(TAG, "onCreatesdrgbrthtrhtyr: " + sCredProvider.getIdentityId());
//        binding.setOnClickListener(v -> {
//            CognitoSettings user = new CognitoSettings(getApplicationContext());
//            CognitoUser cognitoUser = user.getUserPool().getUser("cognitochan");
//            Log.i(TAG, "onCreate: " + cognitoUser.getUserId());
//            Log.i(TAG, "logout: " + cognitoUser.getUserPoolId());
//            cognitoUser.signOut();
//            Log.i(TAG, "logout: SIGNED OUT");
////            cognitoUser.globalSignOut(new GenericHandler() {
////                @Override
////                public void onSuccess() {
////                    Log.i(TAG, "onSuccess: LOGGED OUT");
//////                    Log.i(TAG, "onSuccess: " + sCredProvider.getIdentityId());
//////                    sCredProvider.clear();
//////                    Log.i(TAG, "onSuccess: " + sCredProvider.getIdentityId());
////                }
////
////                @Override
////                public void onFailure(Exception exception) {
////                    Log.i(TAG, "onFailure: failed to log out globally");
////                    Log.i(TAG, "onFailure: " + exception.getLocalizedMessage());
////                    Log.i(TAG, "onFailure: " + exception.getMessage());
////                    Log.i(TAG, "onFailure: " + exception.getCause());
//////                    Log.i(TAG, "FAILED: " + sCredProvider.getIdentityId());
////                }
////            });
//            Log.i(TAG, "logout: " + cognitoUser.getUserPoolId());
//            Log.i(TAG, "onCreate: " + cognitoUser.getUserId());
//        });
//    }
}
