package com.yashkasera.musicwiki.ui.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.util.Result
import kotlinx.coroutines.launch

class TrackViewModel : ViewModel() {
    private val _result: MutableLiveData<Result<Track>> = MutableLiveData()
    val result: LiveData<Result<Track>>
        get() = _result

    fun fetchData(artist: String, track: String) =
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val res = AppObjectController.repository.getTrackInfo(artist, track)
            _result.postValue(res)
        }
}