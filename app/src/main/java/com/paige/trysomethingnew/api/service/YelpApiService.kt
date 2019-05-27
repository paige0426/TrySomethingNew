package com.paige.trysomethingnew.api.service

import com.paige.trysomethingnew.api.response.YelpResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YelpApiService {
    @GET("businesses/search")
    @Headers("Authorization: Bearer $YELP_API_KEY")
    fun loadRestaurants(
        @Query("term") term: String,
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double
    ) : Call<YelpResponse>

    companion object {
        private const val YELP_API_KEY = "cDXGfrkVQ1Rf-brpwZ0yNQoPDr-WxtVrIFKh2QggH2tVdKqZQKjboRSFka9ZqrDiFUGl9UPCOlrrsXkPm7fO_a_YHNOMI-5r3h1ytcH_06V4aMOZbuyFcGpUDAvrXHYx"
    }
}