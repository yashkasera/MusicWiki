package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("streamable")
    val streamable: Streamable? = null,
    @SerializedName("listeners")
    val listeners: String? = null,
    @SerializedName("playcount")
    val playcount: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("image")
    val image: List<ImageData>? = null,
    @SerializedName("@attr")
    val attr: Attr? = null,
    @SerializedName("album")
    val album: Album? = null,
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("wiki")
    val wiki: Wiki? = null,
    @SerializedName("toptags")
    val genres: Genres? = null
) : BaseModel(name)