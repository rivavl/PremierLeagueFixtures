package com.marina.premierleaguefixtures.domain.use_case

import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.domain.repository.MatchRepository
import com.marina.premierleaguefixtures.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMatchesUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(query: String?): Flow<Resource<List<Match>>> {
        return repository.getAllMatches(query)
    }
}