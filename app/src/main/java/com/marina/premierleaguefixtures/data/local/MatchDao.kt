package com.marina.premierleaguefixtures.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marina.premierleaguefixtures.data.local.model.MatchDB

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMatches(matches: List<MatchDB>)

    @Query("select * from `match`")
    suspend fun getMatches(): List<MatchDB>

    @Query("select * from `match` where matchNumber=:id")
    suspend fun getMatch(id: Int): MatchDB
}