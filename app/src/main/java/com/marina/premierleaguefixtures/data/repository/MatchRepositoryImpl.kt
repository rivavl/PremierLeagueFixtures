package com.marina.premierleaguefixtures.data.repository

import android.util.Log
import com.marina.premierleaguefixtures.data.local.MatchDao
import com.marina.premierleaguefixtures.data.local.model.MatchDB
import com.marina.premierleaguefixtures.data.mapper.fromDBToDomain
import com.marina.premierleaguefixtures.data.mapper.fromDtoToDB
import com.marina.premierleaguefixtures.data.mapper.toDomain
import com.marina.premierleaguefixtures.data.remote.MatchApi
import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.domain.repository.MatchRepository
import com.marina.premierleaguefixtures.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val api: MatchApi,
    private val dao: MatchDao
) : MatchRepository {

    override suspend fun getAllMatches(query: String?): Flow<Resource<List<Match>>> = flow {
        emit(Resource.Loading())
        emit(loadFromDB(query))
        loadFromApi()
        emit(loadFromDB(query))
    }

    override suspend fun getMatchById(id: Int): Flow<Resource<Match>> = flow {
        emit(Resource.Loading())
        try {
            val match = dao.getMatch(id).toDomain()
            emit(Resource.Success(match))
        } catch (e: Exception) {
            emit(Resource.Error())
        }
    }

    private suspend fun loadFromDB(query: String?): Resource<List<Match>> {
        return try {
            val fromDB = dao.getMatches(query).fromDBToDomain()
            Resource.Success(fromDB)
        } catch (e: Exception) {
            Resource.Error()
        }
    }

    private suspend fun loadFromApi() {
        try {
            val matches = api.getMatchesList()
            saveIntoDB(matches.fromDtoToDB())
        } catch (e: Exception) {
            Log.e(this.javaClass.simpleName, e.message.toString())
        }
    }

    private suspend fun saveIntoDB(matches: List<MatchDB>) {
        dao.saveMatches(matches)
    }
}