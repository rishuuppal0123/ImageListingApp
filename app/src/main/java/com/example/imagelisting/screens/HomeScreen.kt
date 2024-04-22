package com.example.imagelisting.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imagelisting.R

@Composable
fun HomeScreen(toImageList: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to ImagePedia",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center
            )
        )
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "app logo",
            modifier = Modifier.size(200.dp).fillMaxSize()
        )
        Button(onClick = {
            toImageList()
        }) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Click here to go ahead",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
        }
    }
}