package com.marina.premierleaguefixtures.di.module

import androidx.lifecycle.ViewModel
import com.marina.premierleaguefixtures.di.annotation.ViewModelKey
import com.marina.premierleaguefixtures.presentation.detail.DetailViewModel
import com.marina.premierleaguefixtures.presentation.list.ListFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    fun bindListViewModel(viewModel: ListFragmentViewModel): ViewModel
}