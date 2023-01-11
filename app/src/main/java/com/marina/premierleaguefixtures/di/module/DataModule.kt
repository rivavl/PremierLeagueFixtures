package com.marina.premierleaguefixtures.di.module

import android.app.Application
import com.marina.premierleaguefixtures.data.local.AppDatabase
import com.marina.premierleaguefixtures.data.local.MatchDao
import com.marina.premierleaguefixtures.data.remote.MatchApi
import com.marina.premierleaguefixtures.data.remote.RetrofitInstance
import com.marina.premierleaguefixtures.data.repository.MatchRepositoryImpl
import com.marina.premierleaguefixtures.di.annotation.ApplicationScope
import com.marina.premierleaguefixtures.domain.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMatchRepository(impl: MatchRepositoryImpl): MatchRepository

    companion object {
        @Provides
        fun provideMatchApi(): MatchApi {
            return RetrofitInstance.matchesApi
        }

        @Provides
        fun provideMatchDao(application: Application): MatchDao {
            return AppDatabase.getInstance(application).matchDao
        }
    }

}