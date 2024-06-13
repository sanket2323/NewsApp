package com.example.newsapp.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    val baseUrl ="https://newsapi.org/"

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsService = retrofit.create(ApiService::class.java)
}