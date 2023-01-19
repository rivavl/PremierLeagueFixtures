package com.marina.premierleaguefixtures.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.premierleaguefixtures.domain.use_case.GetAllMatchesUseCase
import com.marina.premierleaguefixtures.domain.util.Resource
import com.marina.premierleaguefixtures.presentation.entity.MatchUI
import com.marina.premierleaguefixtures.presentation.mapper.toUI
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(
    private val getAllMatchesUseCase: GetAllMatchesUseCase
) : ViewModel() {

    private val _matchesList = MutableLiveData<Resource<List<MatchUI>>>()
    val matchesList: LiveData<Resource<List<MatchUI>>> get() = _matchesList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        loadMatches("")
    }

    fun loadMatches(query: String?) = viewModelScope.launch {
        getAllMatchesUseCase.invoke(query).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _matchesList.postValue(
                        Resource.Success(result.data!!.toUI())
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