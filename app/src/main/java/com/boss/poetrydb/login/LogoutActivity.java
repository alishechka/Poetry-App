package com.boss.poetrydb.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.example.cognito.R;

public class LogoutActivity extends AppCompatActivity {
    private Button btn;
    private static final String TAG = "LogoutActivity";
    private static CognitoCachingCredentialsProvider sCredProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        btn = findViewById(R.id.button_logout);

        Log.i(TAG, "onCreatesdrgbrthtrhtyr: " + sCredProvider.getIdentityId());
        btn.setOnClickListener(v -> {

            CognitoSettings user = new CognitoSettings(getApplicationContext());
            CognitoUser cognitoUser = user.getUserPool().getUser("cognitochan");
            Log.i(TAG, "onCreate: " + cognitoUser.getUserId());

            cognitoUser.globalSignOut(new GenericHandler() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "onSuccess: logged out globally");
                }

                @Override
                public void onFailure(Exception exception) {
                    Log.i(TAG, "onFailure: failed to log out globally");
                    Log.i(TAG, "onFailure: " + exception.getLocalizedMessage());
                    Log.i(TAG, "onFailure: " + exception.getMessage());
                    Log.i(TAG, "onFailure: " + exception.getCause());
                }
            });
            Log.i(TAG, "onCreate: " + cognitoUser.getUserId());
        });

    }
}