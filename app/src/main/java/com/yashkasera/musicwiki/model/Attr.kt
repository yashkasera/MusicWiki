package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("rank") val rank: Int?,
    @SerializedName("num_res") val numRes: Int?,
    @SerializedName("offset") val offset: Int?,
    @SerializedName("total") val total: Int?
)