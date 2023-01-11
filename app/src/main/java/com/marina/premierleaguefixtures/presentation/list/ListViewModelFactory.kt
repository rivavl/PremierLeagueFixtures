package com.marina.premierleaguefixtures.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.premierleaguefixtures.domain.use_case.GetAllMatchesUseCase

class ListViewModelFactory(
    private val useCase: GetAllMatchesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListFragmentViewModel(useCase) as T
    }
}