package com.example.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ApiService.ApiInstance.newsService
import com.example.newsapp.ApiService.Article
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    data class appState(
        val isLoading: Boolean = true,
        val error: String? = null,
        val list: List<Article> = emptyList()
    )

    init {
        fetchNews()
    }

    private var _newsState = mutableStateOf(appState())
    val newsState: State<appState> = _newsState

    private fun fetchNews() {
        viewModelScope.launch {

            try {
                val response = newsService.getNews()
                _newsState.value = _newsState.value.copy(
                    isLoading = false,
                    error = null,
                    list = response.articles
                )
            } catch (e: Exception) {
                _newsState.value = _newsState.value.copy(
                    isLoading = false,
                    error = "Something went wrong ${e.message}",
                    list = emptyList()
                )
            }
        }
    }



}