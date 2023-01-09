package com.marina.premierleaguefixtures.data.local

import com.marina.premierleaguefixtures.data.local.model.MatchDB
import com.marina.premierleaguefixtures.data.mapper.fromDBToUI
import com.marina.premierleaguefixtures.model.Match

class DatabaseHelper(
    private val dao: MatchDao
) {
    suspend fun getMatches(): List<Match> {
        return dao.getMatches().fromDBToUI()
    }

    suspend fun saveMatch(match: MatchDB) {
        dao.saveMatch(match)
    }
}