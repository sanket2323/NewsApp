package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.ApiService.Article

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {

    val newsViewModel: MainViewModel = viewModel()
    val newsAppState by newsViewModel.newsState

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            newsAppState.isLoading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            newsAppState.error != null -> {
                Text(text = "Error loading the data")
            }

            else -> {
                //Display list
                MainScreen(news = newsAppState.list)
            }
        }
    }
}

@Composable
fun MainScreen(news: List<Article>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(0.dp, 50.dp)
    ) {
        items(news) {
            LazyColItem(news = it)
        }
    }
}


@Composable
fun LazyColItem(news: Article) {
    Card(
        modifier = Modifier
            .fillMaxSize().padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column {
            Text(text = news.title,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .wrapContentWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
            Image(
                painter = rememberAsyncImagePainter(model = news.urlToImage),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp),
                alignment = Alignment.Center
            )
            Text(text = news.content,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium)
        }

    }

}