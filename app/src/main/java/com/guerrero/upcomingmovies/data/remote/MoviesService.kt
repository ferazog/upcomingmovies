package com.guerrero.upcomingmovies.data.remote

import com.guerrero.upcomingmovies.BuildConfig
import com.guerrero.upcomingmovies.data.ApiGetUpcomingListResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/upcoming")
    suspend fun getUpcomingList(
        @Query("api_key") apiKey: String = BuildConfig.WEB_API_KEY,
        @Query("page") page: Int
    ): ApiGetUpcomingListResult
}
