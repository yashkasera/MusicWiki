package com.yashkasera.musicwiki.service

import com.yashkasera.musicwiki.BuildConfig
import com.yashkasera.musicwiki.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DIR = BuildConfig.API_VERSION

interface CommonNetworkService {
    @GET("$DIR/")
    suspend fun getGenres(
        @Query("method") method: String = "tag.getTopTags"
    ): Response<TopGenres>


    @GET("$DIR/")
    suspend fun getTagInfo(
        @Query("method") method: String = "tag.getInfo",
        @Query("tag") genre: String
    ): Response<GenreWrapper>

    @GET("$DIR/")
    suspend fun getAlbumsOf(
        @Query("method") method: String = "tag.getTopAlbums",
        @Query("tag") genre: String
    ): Response<AlbumsWrapper>

    @GET("$DIR/")
    suspend fun getArtistsOf(
        @Query("method") method: String = "tag.getTopArtists",
        @Query("tag") genre: String
    ): Response<TopArtists>

    @GET("$DIR/")
    suspend fun getTracksOf(
        @Query("method") method: String = "tag.getTopTracks",
        @Query("tag") genre: String
    ): Response<TracksWrapper>

    @GET("$DIR/")
    suspend fun getArtistInfo(
        @Query("method") method: String = "artist.getInfo",
        @Query("artist") name: String,
    ): Response<ArtistWrapper>

    @GET("$DIR/")
    suspend fun getTopAlbums(
        @Query("method") method: String = "artist.getTopAlbums",
        @Query("artist") name: String,
    ): Response<TopAlbumsWrapper>

    @GET("$DIR/")
    suspend fun getAlbumInfo(
        @Query("method") method: String = "album.getInfo",
        @Query("album") album: String,
        @Query("artist") artist: String,
    ): Response<AlbumWrapper>

    @GET("$DIR/")
    suspend fun getTrackInfo(
        @Query("method") method: String = "track.getInfo",
        @Query("track") track: String,
        @Query("artist") artist: String,
    ): Response<TrackWrapper>

}