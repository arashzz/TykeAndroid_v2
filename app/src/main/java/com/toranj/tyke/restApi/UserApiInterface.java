package com.toranj.tyke.restApi;

import com.toranj.tyke.models.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by arash on 8/18/16.
 */
public interface UserApiInterface {

    @POST("/User/Register")
    Call<User> register(@Query("user") User user);
}
