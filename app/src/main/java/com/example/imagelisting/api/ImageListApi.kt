package com.example.imagelisting.api

import com.example.imagelisting.data.ImageData
import retrofit2.http.GET

interface ImageListApi {

    @GET("api/v2/content/misc/media-coverages?limit=100")
    suspend fun getImages(): List<ImageData>
}