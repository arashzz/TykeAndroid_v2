package com.toranj.tyke.restApi;

import com.toranj.tyke.models.Tag;
import com.toranj.tyke.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by arash on 8/18/16.
 */
public interface TagApiInterface {

    @GET("/tag/getForUser/")
    Call<List<Tag>> getTagsForUser(@Query("user") User user);

    @POST("/tag/addForUser")
    Call<Boolean> addTagsForUser(@Query("user") User user, @Query("tags") List<Tag> tags);
}
