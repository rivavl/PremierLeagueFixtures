package com.marina.premierleaguefixtures.domain.repository

import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getAllMatches(query: String?): Flow<Resource<List<Match>>>

    suspend fun getMatchById(id: Int): Flow<Resource<Match>>
}