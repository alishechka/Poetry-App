package com.example.cognito.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.example.cognito.MainActivity;
import com.example.cognito.PoetryServiceActivity;
import com.example.cognito.R;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignIn";
    EditText username;
    EditText password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.username_singin);
        password = findViewById(R.id.password_signin);
        signIn = findViewById(R.id.click_signin);

        // Callback handler for the sign-in process

        signIn.setOnClickListener(v -> {

            AuthenticationHandler authenticationHandler = new AuthenticationHandler() {

                @Override
                public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                    Log.d(TAG, "onSuccess: login SUCCESS, can get TOKENS");
                    Log.d(TAG, "onSuccess: TOKEN id: " + userSession.getIdToken().getJWTToken());
                    Log.d(TAG, "onSuccess: TOKEN access: " + userSession.getAccessToken().getJWTToken());
                    Log.d(TAG, "onSuccess: TOKEN refresh " + userSession.getRefreshToken().getToken());

                    Toast.makeText(SignInActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
//                    CognitoSettings user2 = new CognitoSettings(getApplicationContext());
//                    CognitoUser i = user2.getUserPool().getUser(userSession.getUsername());
//                    sCredProvider=new CognitoCachingCredentialsProvider(SignInActivity.this,
//                            i.getUserId(),i.getUserPoolId(),null,null,user2.getCognitoRegion());

                }

                @Override
                public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
                    // The API needs user sign-in credentials to continue
                    AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, String.valueOf(password.getText()), null);

                    // Pass the user sign-in credentials to the continuation
                    authenticationContinuation.setAuthenticationDetails(authenticationDetails);

                    // Allow the sign-in to continue
                    authenticationContinuation.continueTask();

                }

                @Override
                public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
//                // Multi-factor authentication is required, get the verification code from user
//                multiFactorAuthenticationContinuation.setMfaCode(mfaVerificationCode);
//                // Allow the sign-in process to continue
//                multiFactorAuthenticationContinuation.continueTask();
                }

                @Override
                public void authenticationChallenge(ChallengeContinuation continuation) {

                }

                @Override
                public void onFailure(Exception exception) {
                    // Sign-in failed, check exception for the cause
                    Log.d(TAG, "onFailure: login" + exception.getLocalizedMessage());
                    Toast.makeText(SignInActivity.this, "login failed: " + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            };

            // Sign-in the user
            CognitoSettings user = new CognitoSettings(getApplicationContext());
            CognitoUser cognitoUser = user.getUserPool().getUser(String.valueOf(username.getText()));
            cognitoUser.getSessionInBackground(authenticationHandler);

            Intent intent = new Intent(this, PoetryServiceActivity.class);
            startActivity(intent);
        });
    }
}