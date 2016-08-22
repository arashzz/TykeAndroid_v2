package com.toranj.tyke.restApi;

import com.toranj.tyke.models.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by arash on 8/12/16.
 */
public interface AuthenticationApiInterface {

    @POST("/user/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @POST("/user/authenticate")
    Call<User> authenticate(@Query("token") String token);
}
