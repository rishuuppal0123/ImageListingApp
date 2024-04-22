package com.example.imagelisting.di

import com.example.imagelisting.repo.ImageListRepo
import com.example.imagelisting.repo.ImageListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ImageListModule {
    @Binds
    abstract fun provideImageListModule(imageListRepoImpl: ImageListRepoImpl): ImageListRepo
}