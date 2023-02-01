package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class TopArtists(
    @SerializedName("topartists") val topArtists: ArtistListWrapper
)

data class ArtistListWrapper(
    @SerializedName("artist") val artist: List<Artist>,

    @SerializedName("@attr") val attr: Attr?
)

data class ArtistWrapper(
    @SerializedName("artist") val artist: Artist,
)

data class SimilarArtistWrapper(
    @SerializedName("similarartists") val similarArtists: ArtistListWrapper
)