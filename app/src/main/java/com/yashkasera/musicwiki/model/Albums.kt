package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class AlbumsWrapper(
    @SerializedName("albums")
    val albums: Albums
)

data class Albums(
    @SerializedName("album")
    val albums: List<Album>
)

data class TopAlbumsWrapper(
    @SerializedName("topalbums")
    val topAlbums: Albums
)

data class AlbumWrapper(
    @SerializedName("album")
    val album: Album
)