package com.example.newsapp.ApiService

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)