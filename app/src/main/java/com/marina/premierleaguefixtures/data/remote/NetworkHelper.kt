package com.marina.premierleaguefixtures.data.remote

import com.marina.premierleaguefixtures.data.Resource
import com.marina.premierleaguefixtures.data.mapper.toUI
import com.marina.premierleaguefixtures.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkHelper(private val api: MatchApi) {

    fun getMatches(): Flow<Resource<List<Match>>> = flow {
        try {
            emit(Resource.Loading())
            val matches = api.getMatchesList().toUI()
            emit(Resource.Success(matches))
        } catch (e: Exception) {
            emit(Resource.Error())
        }
    }
}