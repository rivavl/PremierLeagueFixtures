package com.marina.premierleaguefixtures.presentation.list

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.marina.premierleaguefixtures.domain.use_case.GetAllMatchesUseCase
import com.marina.premierleaguefixtures.domain.util.Resource
import com.marina.premierleaguefixtures.presentation.entity.MatchUI
import com.marina.premierleaguefixtures.presentation.mapper.toUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListFragmentViewModel(
    private val useCase: GetAllMatchesUseCase
) : ViewModel() {

    private val _matchesList = MutableLiveData<Resource<List<MatchUI>>>()
    val matchesList: LiveData<Resource<List<MatchUI>>> get() = _matchesList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        loadMatches()
    }

    private fun loadMatches() = viewModelScope.launch {
        useCase.invoke().collect { result ->
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