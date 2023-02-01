package com.yashkasera.musicwiki.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @SerializedName("count") val count: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("reach") val reach: Int? = null,
    @SerializedName("wiki") val wiki: Wiki? = null,
    @SerializedName("url") val url: String? = null,
) : Parcelable {
    @IgnoredOnParcel
    var isSelected: Boolean = false
}

data class GenreWrapper(
    @SerializedName("tag") val genre: Genre
)