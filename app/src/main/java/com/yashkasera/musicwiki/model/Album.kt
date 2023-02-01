package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("artist") val artist: Any? = null,
    @SerializedName("image") val imageData: List<ImageData>? = null,
    @SerializedName("listeners") val listeners: String? = null,
    @SerializedName("mbid") val mbid: String? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("title") val title: String? = null,
    @SerializedName("playcount") val playcount: String? = null,
    @SerializedName("tags") val genres: Genres? = null,
    @SerializedName("tracks") val tracks: Tracks? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("wiki") val wiki: Wiki? = null,
    @SerializedName("@attr") val attr: Attr? = null
) : BaseModel(name) {
    fun getArtistName(): String = when (artist) {
        is Map<*, *> -> artist["name"] as String
        is String -> artist
        else -> "Unknown"
    }
}