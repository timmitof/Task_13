package com.timmitof.retrofit.services

import com.timmitof.retrofit.models.ResponseWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String
    ) : Call<ResponseWeather>
}