package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("listeners")
    val listeners: String,

    @SerializedName("playcount")
    val playcount: String
)