package com.yashkasera.musicwiki.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.model.Genre
import com.yashkasera.musicwiki.util.Result
import kotlinx.coroutines.launch

class GenreViewModel : ViewModel() {
    private val _result: MutableLiveData<Result<List<Genre>>> = MutableLiveData()
    val result: LiveData<Result<List<Genre>>>
        get() = _result

    init {
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val res = AppObjectController.repository.getGenres()
            _result.postValue(res)
        }
    }
}