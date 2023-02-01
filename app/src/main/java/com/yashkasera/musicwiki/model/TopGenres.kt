package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class TopGenres(
    @SerializedName("toptags") val toptags: Genres
)
