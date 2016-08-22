package com.toranj.tyke.restApi;

import com.toranj.tyke.models.Brand;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arash on 8/13/16.
 */
public interface BrandApiInterface {

    @GET("/brand/index")
    Call<List<Brand>> getBrands();

    @GET("/brand/detail")
    Call<Brand> getBrandById(@Query("id") String id);
}
