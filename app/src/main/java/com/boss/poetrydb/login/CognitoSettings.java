package com.boss.poetrydb.login;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSettings {
    private String userPoolId = "eu-west-2_KDXuCpLCw";
    private String clientId = "1gq3a1etpbsm8ir7gm8fdhh3r2";
    private String clientSecret = "fbslpvnos46qkifqtko92klgtf6rhs2njuqnahfop0oue34kh99";
    private Regions cognitoRegion = Regions.EU_WEST_2;

    private Context context;

    public CognitoSettings(Context context) {
        this.context = context;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Regions getCognitoRegion() {
        return cognitoRegion;
    }

    public CognitoUserPool getUserPool() {
        return new CognitoUserPool(context, userPoolId, clientId, clientSecret, cognitoRegion);
    }
}
