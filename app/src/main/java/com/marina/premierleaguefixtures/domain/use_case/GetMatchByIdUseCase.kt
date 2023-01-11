package com.marina.premierleaguefixtures.domain.use_case

import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.domain.repository.MatchRepository
import com.marina.premierleaguefixtures.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMatchByIdUseCase(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Match>> {
        return repository.getMatchById(id)
    }
}