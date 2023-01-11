package com.marina.premierleaguefixtures.presentation.entity

data class MatchUI(
    val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val group: String?,
    val homeTeamScore: Int,
    val awayTeamScore: Int,
    val homeWins: Boolean,
    val awayWins: Boolean
)