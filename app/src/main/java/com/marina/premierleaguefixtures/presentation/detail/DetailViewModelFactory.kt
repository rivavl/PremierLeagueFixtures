package com.marina.premierleaguefixtures.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.premierleaguefixtures.domain.use_case.GetAllMatchesUseCase
import com.marina.premierleaguefixtures.domain.use_case.GetMatchByIdUseCase

class DetailViewModelFactory(
    private val useCase: GetMatchByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(useCase) as T
    }
}