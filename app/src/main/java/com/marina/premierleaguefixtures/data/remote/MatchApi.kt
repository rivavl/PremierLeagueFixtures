package com.marina.premierleaguefixtures.data.remote

import com.marina.premierleaguefixtures.data.remote.dto.MatchDto
import retrofit2.http.GET

interface MatchApi {
    @GET(".")
    suspend fun getMatchesList(): List<MatchDto>
}