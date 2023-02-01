package com.yashkasera.musicwiki.ui.genredetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.model.Genre
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.util.Result
import kotlinx.coroutines.launch

class GenreDetailsViewModel : ViewModel() {
    private val _genreResult: MutableLiveData<Result<Genre>> = MutableLiveData()
    val genreResult: LiveData<Result<Genre>>
        get() = _genreResult

    private val _albumResult: MutableLiveData<Result<List<Album>>> = MutableLiveData()
    val albumResult: LiveData<Result<List<Album>>>
        get() = _albumResult

    private val _artistResult: MutableLiveData<Result<List<Artist>>> = MutableLiveData()
    val artistResult: LiveData<Result<List<Artist>>>
        get() = _artistResult

    private val _trackResult: MutableLiveData<Result<List<Track>>> = MutableLiveData()
    val trackResult: LiveData<Result<List<Track>>>
        get() = _trackResult

    fun getGenreInfo(genre: Genre) {
        viewModelScope.launch {
            _genreResult.postValue(Result.Loading())
            val res = AppObjectController.repository.getGenreInfo(genre.name)
            _genreResult.postValue(res)
        }
    }

    fun getAlbums(genre: Genre) = viewModelScope.launch {
        _albumResult.postValue(Result.Loading())
        AppObjectController.repository.getAlbums(genre.name).let {
            _albumResult.postValue(it)
        }
    }

    fun getArtists(genre: Genre) = viewModelScope.launch {
        _artistResult.postValue(Result.Loading())
        AppObjectController.repository.getArtists(genre.name).let {
            _artistResult.postValue(it)
        }
    }

    fun getTracks(genre: Genre) = viewModelScope.launch {
        _trackResult.postValue(Result.Loading())
        AppObjectController.repository.getTracks(genre.name).let {
            _trackResult.postValue(it)
        }
    }

}