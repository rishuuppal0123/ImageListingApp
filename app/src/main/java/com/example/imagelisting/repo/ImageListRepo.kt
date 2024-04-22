package com.example.imagelisting.repo

import com.example.imagelisting.common.ApiResult
import com.example.imagelisting.data.ImageData

interface ImageListRepo {
    suspend fun getImageList(): ApiResult<List<ImageData>>
}