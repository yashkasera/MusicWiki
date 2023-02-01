package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("tag")
    val genres: List<Genre>
)