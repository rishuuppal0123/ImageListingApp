package com.example.imagelisting.repo

import com.example.imagelisting.api.ImageListApi
import com.example.imagelisting.common.ApiResult
import com.example.imagelisting.common.ApiService
import com.example.imagelisting.data.ImageData
import javax.inject.Inject

class ImageListRepoImpl @Inject constructor(
    private val apiService: ApiService
): ImageListRepo {
    private val service = apiService.buildService(ImageListApi::class.java)
    override suspend fun getImageList(): ApiResult<List<ImageData>> {
        return apiService.getApiResult {
            service.getImages()
        }
    }
}