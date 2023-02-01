package com.yashkasera.musicwiki.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.util.Result
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {
    private val _result: MutableLiveData<Result<Album>> = MutableLiveData()
    val result: LiveData<Result<Album>>
        get() = _result

    fun fetchData(artist: String, album: String) =
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val res = AppObjectController.repository.getAlbum(artist, album)
            _result.postValue(res)
        }

}