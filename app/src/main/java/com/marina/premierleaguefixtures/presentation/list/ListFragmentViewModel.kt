package com.marina.premierleaguefixtures.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.premierleaguefixtures.data.mapper.toUI
import com.marina.premierleaguefixtures.data.remote.RetrofitInstance
import com.marina.premierleaguefixtures.model.Match
import kotlinx.coroutines.launch

class ListFragmentViewModel : ViewModel() {

    // пока так, когда добавлю di, исправлю
    private val api = RetrofitInstance.matchesApi

    private val _matchesList = MutableLiveData<List<Match>>()
    val matchesList: LiveData<List<Match>> get() = _matchesList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadMatches()
    }

    private fun loadMatches() = viewModelScope.launch {
        setLoading(true)
        val list = api.getMatchesList().toUI()
        _matchesList.value = list
        setLoading(false)
    }

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}