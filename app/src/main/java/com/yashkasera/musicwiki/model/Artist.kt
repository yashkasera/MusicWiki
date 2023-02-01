package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("mbid") val mbid: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String? = null,
    @SerializedName("streamable") val streamable: String? = null,
    @SerializedName("ontour") val onTour: String? = null,
    @SerializedName("stats") val stats: Stats? = null,
    @SerializedName("image") val image: List<ImageData>? = null,
    @SerializedName("similar") val similar: ArtistListWrapper? = null,
    @SerializedName("tags") val tags: Genres? = null,
    @SerializedName("bio") val bio: Wiki? = null,
    @SerializedName("@attr") val attr: Attr? = null
): BaseModel(name)