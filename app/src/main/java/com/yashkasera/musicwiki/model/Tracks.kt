package com.yashkasera.musicwiki.model


import com.google.gson.annotations.SerializedName

data class TrackWrapper(
    @SerializedName("track")
    val track: Track
)

data class TracksWrapper(
    @SerializedName("tracks")
    val tracks: Tracks
)

data class Tracks(
    @SerializedName("track")
    val track: List<Track>
)