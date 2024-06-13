package com.example.newsapp.ApiService

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")

    suspend fun getNews(
        @Query("q") query: String="business",
        @Query("from") fromDate: String = "2024-05-13",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apikey") apikey:String = "e723ff2fe2274b608cd03dd96845fce7"
    ): NewsModel
}