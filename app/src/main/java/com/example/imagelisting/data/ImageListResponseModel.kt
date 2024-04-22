package com.example.imagelisting.data

data class ImageData(
    val id: String,
    val title: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val id: String,
    val domain: String,
    val basePath: String,
    val key: String
)