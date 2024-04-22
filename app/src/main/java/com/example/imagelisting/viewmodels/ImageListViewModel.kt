package com.example.imagelisting.viewmodels

import android.graphics.ImageDecoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagelisting.common.ApiResult
import com.example.imagelisting.data.ImageData
import com.example.imagelisting.di.IoDispatcher
import com.example.imagelisting.repo.ImageListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageListRepo: ImageListRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _uiState = mutableStateOf(ImageListUiState())
    val uiState: State<ImageListUiState> = _uiState


    fun getImages() {
        _uiState.value = ImageListUiState(
            isLoading = true
        )
        viewModelScope.launch(ioDispatcher) {
            when (val result = imageListRepo.getImageList()) {
                is ApiResult.Error -> {
                    _uiState.value = ImageListUiState(
                        isLoading = false,
                        error = result.message ?: "No images found"
                    )
                }

                is ApiResult.Success -> {
                    _uiState.value = ImageListUiState(
                        isLoading = false,
                        imageList = result.data.map {
                            it.thumbnail.domain + "/" + it.thumbnail.basePath + "/0/" + it.thumbnail.key
                        }
                    )
                }
            }
        }
    }

    data class ImageListUiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val imageList: List<String>? = null
    )
}