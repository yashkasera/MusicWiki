package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val url: String
)