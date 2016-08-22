package com.toranj.tyke.helpers;


import android.accounts.Account;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by arash on 8/12/16.
 */
public class AccountHelper {

    private static final String USER_AUTH_CODE = "auth_code";
    private SharedPreferences sharedPreferences;

    public AccountHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean isUserExist() {
        if(!sharedPreferences.getString(USER_AUTH_CODE, "").isEmpty()) {
            return true;
        }
        return false;
    }

    public String getUserToken() {
        return sharedPreferences.getString(USER_AUTH_CODE, "");
    }

    public void setUserToken(String AuthCode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_AUTH_CODE, AuthCode);
        editor.apply();
    }

    public void removeUserToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_AUTH_CODE);
        editor.apply();
    }

}
