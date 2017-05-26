package com.doordash.rest;

import com.doordash.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

public interface ApiInterface {
        // list of restaurants
        @GET("restaurant/")
        Call<List<Restaurant>> getRestaurantList(@Query("lat") double lat, @Query("lng")  double lng);

        // one restaurant object
        @GET("restaurant/{id}/")
        Call<Restaurant> getRestaurantDetails(@Path("id") int id);



}

