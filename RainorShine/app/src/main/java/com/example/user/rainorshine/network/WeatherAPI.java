package com.example.user.rainorshine.network;

import com.example.user.rainorshine.data.Details;
import com.example.user.rainorshine.data.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 2016. 05. 10..
 */
    public interface WeatherAPI {
    //I think this is correct but i may have messed up something in the query calls
        @GET("weather")
        Call<Details>getName(@Query("q")String name,@Query("units")String metric,@Query("appid")String id );

    }
