package com.yashkasera.musicwiki.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wiki(
    @SerializedName("published") val published: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("content") val content: String?,
) : Parcelable