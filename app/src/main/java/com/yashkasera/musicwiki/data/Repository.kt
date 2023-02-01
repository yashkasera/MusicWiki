package com.yashkasera.musicwiki.data

import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.model.Genre
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.service.CommonNetworkService
import com.yashkasera.musicwiki.util.Result
import com.yashkasera.musicwiki.util.getResult

class Repository(private val commonNetworkService: CommonNetworkService) {

    enum class ApiMethods(val methodName: String) {
        GENRE_SIMILAR("tag.getSimilar"),

        ALBUM_INFO("album.getInfo"),
        ALBUM_SEARCH("album.search"),

        ARTIST_INFO("artist.getInfo"),
        ARTIST_TOP_ALBUMS("artist.getTopAlbums"),
        ARTIST_TOP_TAGS("artist.getTopTags"),
        ARTIST_TOP_TRACKS("artist.getTopTracks"),
        ARTIST_SEARCH("artist.search"),
        ARTIST_SIMILAR("artist.getSimilar"),

    }

    suspend fun getGenres(): Result<List<Genre>> =
        when (val res = getResult { commonNetworkService.getGenres() }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.toptags.genres.let { Result.Success(it) }
            else -> Result.Success(emptyList())
        }

    suspend fun getAlbums(genre: String): Result<List<Album>> =
        when (val res = getResult { commonNetworkService.getAlbumsOf(genre = genre) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.albums.albums.let { Result.Success(it) }
            else -> Result.Success(emptyList())
        }

    suspend fun getGenreInfo(genre: String): Result<Genre> =
        when (val res = getResult { commonNetworkService.getTagInfo(genre = genre) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.genre.let { Result.Success(it) }
            else -> Result.Success(Genre(name = genre))
        }

    suspend fun getArtists(genre: String): Result<List<Artist>> =
        when (val res = getResult { commonNetworkService.getArtistsOf(genre = genre) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.topArtists.artist.let { Result.Success(it) }
            else -> Result.Success(emptyList())
        }

    suspend fun getTracks(genre: String): Result<List<Track>> =
        when (val res = getResult { commonNetworkService.getTracksOf(genre = genre) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.tracks.track.let { Result.Success(it) }
            else -> Result.Success(emptyList())
        }

    suspend fun getArtistInfo(name: String): Result<Artist> =
        when (val res = getResult { commonNetworkService.getArtistInfo(name = name) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.artist.let { Result.Success(it) }
            else -> Result.Success(Artist(name, name = "Unknown"))
        }

    suspend fun getTopAlbums(name: String): Result<List<Album>> =
        when (val res = getResult { commonNetworkService.getTopAlbums(name = name) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.topAlbums.albums.let { Result.Success(it) }
            else -> Result.Success(emptyList())
        }

    suspend fun getAlbum(artist: String, album: String): Result<Album> =
        when (val res = getResult { commonNetworkService.getAlbumInfo(artist = artist, album = album) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.album.let { Result.Success(it) }
            else -> Result.Success(Album(name = album))
        }

    suspend fun getTrackInfo(artist: String, track: String): Result<Track> =
        when (val res = getResult { commonNetworkService.getTrackInfo(artist = artist, track = track) }) {
            is Result.Error -> Result.Error(res.exception)
            is Result.Success -> res.data.track.let { Result.Success(it) }
            else -> Result.Success(Track(name = track, artist = Artist(name = artist)))
        }
}