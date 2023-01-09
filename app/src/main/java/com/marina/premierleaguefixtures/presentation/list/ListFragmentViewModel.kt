package com.marina.premierleaguefixtures.presentation.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.premierleaguefixtures.data.Resource
import com.marina.premierleaguefixtures.data.local.AppDatabase
import com.marina.premierleaguefixtures.data.local.DatabaseHelper
import com.marina.premierleaguefixtures.data.remote.NetworkHelper
import com.marina.premierleaguefixtures.data.remote.RetrofitInstance
import com.marina.premierleaguefixtures.model.Match
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListFragmentViewModel : ViewModel() {

    // пока так, когда добавлю di, исправлю
    private val api = RetrofitInstance.matchesApi
    lateinit var app: Application
    private val databaseHelper by lazy { DatabaseHelper(AppDatabase.getInstance(app).matchDao) }
    private val apiHelper by lazy { NetworkHelper(api, databaseHelper) }

    private val _matchesList = MutableLiveData<Resource<List<Match>>>()
    val matchesList: LiveData<Resource<List<Match>>> get() = _matchesList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        loadMatches()
    }

    private fun loadMatches() = viewModelScope.launch {
        delay(100L)
        apiHelper.getMatches().collect { result ->
            when (result) {
                is Resource.Success -> {
                    _matchesList.postValue(
                        Resource.Success(result.data!!)
                    )
                }
                is Resource.Loading -> {
                    _matchesList.postValue(Resource.Loading())
                }
                is Resource.Error -> {
                    _matchesList.postValue(Resource.Error())
                }
            }
        }
    }
}