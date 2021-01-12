package com.example.cognito.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.example.cognito.MainActivity;
import com.example.cognito.R;

public class ValidateActivity extends AppCompatActivity {

    private static final String TAG = "ValidateActivity";
    Button validate;
    EditText confirmationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        validate = findViewById(R.id.validate_click);

        confirmationCode = findViewById(R.id.validationCode_validate);
        final String username = getIntent().getStringExtra("username");

        validate.setOnClickListener(v -> {
            GenericHandler confirmationCallback = new GenericHandler() {

                @Override
                public void onSuccess() {
                    Log.d(TAG, "onSuccess: confirmationCallback");
                    Toast.makeText(ValidateActivity.this, "Validated Successfully", Toast.LENGTH_SHORT).show();
                    // User was successfully confirmed
                }

                @Override
                public void onFailure(Exception exception) {
                    Log.d(TAG, "onFailure: confirmationCallback " + exception.getMessage());
                    Toast.makeText(ValidateActivity.this, "Validation FAILED: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    // User confirmation failed. Check exception for the cause.
                }
            };

            String code = String.valueOf(confirmationCode.getText());
            CognitoSettings cognitoSettings = new CognitoSettings(getApplicationContext());
            Log.d(TAG, "onClick: CODE " + code);
            CognitoUser cognitoUser = cognitoSettings.getUserPool().getUser(username);
            cognitoUser.confirmSignUpInBackground(
                    code, false, confirmationCallback
            );

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}