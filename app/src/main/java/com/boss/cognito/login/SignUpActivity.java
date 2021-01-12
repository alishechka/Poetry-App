package com.boss.cognito.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.example.cognito.R;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    EditText inputName;
    EditText inputTelephoneNumber;
    EditText inputEmail;
    EditText inputUsername;
    EditText inputPassword;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = findViewById(R.id.inputName_signUp);
        inputTelephoneNumber = findViewById(R.id.inputTelephone);
        inputEmail = findViewById(R.id.inputEmail_signUp);
        inputUsername = findViewById(R.id.inputUserName_signUp);
        inputPassword = findViewById(R.id.inputPassword_signUp);
        signUp = findViewById(R.id.inputSignUp);

        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        // setup AWS service configuration. Choosing default configuration

        signUp.setOnClickListener(v -> {
            userAttributes.addAttribute("given_name", String.valueOf(inputName.getText()));
            userAttributes.addAttribute("phone_number", String.valueOf(inputTelephoneNumber.getText()));
            userAttributes.addAttribute("email", String.valueOf(inputEmail.getText()));
            final SignUpHandler signUpCallback = new SignUpHandler() {

                @Override
                public void onSuccess(CognitoUser cognitoUser, boolean userConfirmed,
                                      CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                    // Sign-up was successful
                    Log.d(TAG, "onSuccess: ...is confirmed " + userConfirmed);
                    // Check if this user (cognitoUser) has to be confirmed
                    if (!userConfirmed) {
                        Log.d(TAG, "onSuccess: ...not confirmed, verification code sent to " + cognitoUserCodeDeliveryDetails.getDestination());
                        Toast.makeText(SignUpActivity.this, "validation sent to: " + cognitoUserCodeDeliveryDetails.getDestination(), Toast.LENGTH_SHORT).show();
                        // This user has to be confirmed and a confirmation code was sent to the user
                        // cognitoUserCodeDeliveryDetails will indicate where the confirmation code was sent
                        // Get the confirmation code from user
                    } else {
                        Log.d(TAG, "onSuccess: ...confirmed");
                        // The user has already been confirmed
                    }

                }

                @Override
                public void onFailure(Exception exception) {
                    Log.d(TAG, "onFailure: " + exception.getLocalizedMessage());
                    // Sign-up failed, check exception for the cause
                }
            };

            final CognitoSettings cognitoSettings = new CognitoSettings(getApplicationContext());
            cognitoSettings.getUserPool().signUpInBackground(
                    String.valueOf(inputUsername.getText()),
                    String.valueOf(inputPassword.getText()),
                    userAttributes, null, signUpCallback

            );

            Intent intent = new Intent(getApplicationContext(), ValidateActivity.class);
            intent.putExtra("username", String.valueOf(inputUsername.getText()));
            startActivity(intent);

        });
    }
}