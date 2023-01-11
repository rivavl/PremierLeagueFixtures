package com.marina.premierleaguefixtures.di.component

import android.app.Application
import com.marina.premierleaguefixtures.di.annotation.ApplicationScope
import com.marina.premierleaguefixtures.di.module.DataModule
import com.marina.premierleaguefixtures.di.module.ViewModelModule
import com.marina.premierleaguefixtures.presentation.detail.DetailsFragment
import com.marina.premierleaguefixtures.presentation.list.ListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: DetailsFragment)
    fun inject(fragment: ListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}