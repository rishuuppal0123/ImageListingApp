package com.example.imagelisting.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagelisting.R
import com.example.imagelisting.commonComposables.EmptyItemsView
import com.example.imagelisting.commonComposables.ErrorLayout
import com.example.imagelisting.viewmodels.ImageListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun ImageListScreen(viewModel: ImageListViewModel = hiltViewModel()) {

    val state = viewModel.uiState.value

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getImages()
    })

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Images fetched for you",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(20.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.DarkGray
                )

            } else if(!state.error.isNullOrEmpty()) {

                ErrorLayout(message = state.error) {
                    viewModel.getImages()
                }

            } else if (state.imageList.isNullOrEmpty()) {

                EmptyItemsView()

            } else {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = rememberLazyGridState(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.imageList) { url ->
                        ImageFromUrl(url = url)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageFromUrl(url: String) {
    val placeholderBitmap = painterResource(id = R.drawable.ic_product_placeholder)
    val loadedBitmap = remember(url) { mutableStateOf<Bitmap?>(null) }

    var loading by remember { mutableStateOf(true) }

    var error by remember { mutableStateOf(false) }

    LaunchedEffect(url) {
        if (loadedBitmap.value == null && !error) {
            val bitmap = withContext(Dispatchers.IO) {
                try {
                    val connection = URL(url).openConnection() as HttpURLConnection
                    connection.connect()
                    val stream = connection.inputStream
                    BitmapFactory.decodeStream(stream)
                } catch (e: Exception) {
                    e.printStackTrace()
                    error = true
                    null
                } finally {
                    loading = false
                }
            }
            loadedBitmap.value = bitmap
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .aspectRatio(1f)
            .padding(2.dp)
    ) {
        if (loading) {
            Image(
                painter = placeholderBitmap,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        } else if (error || loadedBitmap.value == null) {
            Text(text = "Not Found", textAlign = TextAlign.Center)
        } else {
            val bitmap = loadedBitmap.value!!
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(4.dp))
            )
        }
    }
}