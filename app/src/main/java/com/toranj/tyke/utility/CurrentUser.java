package com.toranj.tyke.utility;

import com.toranj.tyke.models.User;

/**
 * Created by arash on 8/13/16.
 */
public class CurrentUser {

    public static String name;
    public static String username;
    public static String token;

    public static void setInfo(User user) {
        //TODO: replace dummy values with user information
        CurrentUser.name = "اصغر آقا قصاب";
        CurrentUser.username = "۰۹۱۲۱۳۸۵۸۵۵";
        CurrentUser.token = "E123";
    }
}
