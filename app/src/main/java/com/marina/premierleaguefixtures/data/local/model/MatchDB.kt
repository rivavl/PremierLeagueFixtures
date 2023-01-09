package com.marina.premierleaguefixtures.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "match", indices = [Index(value = ["MatchNumber"], unique = true)])
data class MatchDB(
    @PrimaryKey
    @ColumnInfo(name = "MatchNumber")
    val matchNumber: Int,
    @ColumnInfo(name = "AwayTeam")
    val awayTeam: String,
    @ColumnInfo(name = "AwayTeamScore")
    val awayTeamScore: Int,
    @ColumnInfo(name = "DateUtc")
    val dateUtc: String,
    @ColumnInfo(name = "Group")
    val group: String?,
    @ColumnInfo(name = "HomeTeam")
    val homeTeam: String,
    @ColumnInfo(name = "HomeTeamScore")
    val homeTeamScore: Int,
    @ColumnInfo(name = "Location")
    val location: String,
    @ColumnInfo(name = "RoundNumber")
    val roundNumber: Int
)