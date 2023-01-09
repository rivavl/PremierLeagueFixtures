package com.marina.premierleaguefixtures.data.remote

import com.marina.premierleaguefixtures.data.Resource
import com.marina.premierleaguefixtures.data.local.DatabaseHelper
import com.marina.premierleaguefixtures.data.mapper.toDB
import com.marina.premierleaguefixtures.data.mapper.toUI
import com.marina.premierleaguefixtures.data.remote.dto.MatchDto
import com.marina.premierleaguefixtures.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkHelper(
    private val api: MatchApi,
    private val databaseHelper: DatabaseHelper
) {

    suspend fun getMatches(): Flow<Resource<List<Match>>> = flow {
        emit(Resource.Loading())

        try {
            val fromDB = databaseHelper.getMatches()
            emit(Resource.Success(fromDB))
        } catch (e: Exception) {
            emit(Resource.Error())
        }

        try {
            val matches = api.getMatchesList()
            saveToDB(matches)
            emit(Resource.Success(matches.toUI()))
        } catch (e: Exception) {
            emit(Resource.Error())
        }
    }

    private suspend fun saveToDB(matches: List<MatchDto>) {
        matches.forEach {
            databaseHelper.saveMatch(it.toDB())
        }
    }
}