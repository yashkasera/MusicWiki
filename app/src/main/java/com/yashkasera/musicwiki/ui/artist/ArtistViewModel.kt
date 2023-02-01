package com.yashkasera.musicwiki.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.util.Result
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {
    private val _artistResult: MutableLiveData<Result<Artist>> = MutableLiveData()
    val artistResult: LiveData<Result<Artist>>
        get() = _artistResult

    private val _topAlbumResult: MutableLiveData<Result<List<Album>>> = MutableLiveData()
    val topAlbumResult: LiveData<Result<List<Album>>>
        get() = _topAlbumResult

    fun fetchData(name: String) {
        getArtistInfo(name)
        getTopAlbums(name)
    }

    fun getArtistInfo(name: String) {
        viewModelScope.launch {
            _artistResult.postValue(Result.Loading())
            val res = AppObjectController.repository.getArtistInfo(name)
            _artistResult.postValue(res)
        }
    }

    fun getTopAlbums(name: String) = viewModelScope.launch {
        _topAlbumResult.postValue(Result.Loading())
        AppObjectController.repository.getTopAlbums(name).let {
            _topAlbumResult.postValue(it)
        }
    }
}
