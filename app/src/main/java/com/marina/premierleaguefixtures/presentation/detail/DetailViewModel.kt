package com.marina.premierleaguefixtures.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.premierleaguefixtures.domain.use_case.GetMatchByIdUseCase
import com.marina.premierleaguefixtures.domain.util.Resource
import com.marina.premierleaguefixtures.presentation.entity.MatchUI
import com.marina.premierleaguefixtures.presentation.mapper.toUI
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: GetMatchByIdUseCase
) : ViewModel() {

    private val _match = MutableLiveData<Resource<MatchUI>>()
    val match: LiveData<Resource<MatchUI>> get() = _match

    fun loadMatchInfo(id: Int) = viewModelScope.launch {
        useCase.invoke(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _match.postValue(
                        Resource.Success(result.data!!.toUI())
                    )
                }
                is Resource.Loading -> {
                    _match.postValue(Resource.Loading())
                }
                is Resource.Error -> {
                    _match.postValue(Resource.Error())
                }
            }
        }
    }
}