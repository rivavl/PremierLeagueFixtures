package com.marina.premierleaguefixtures.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("AwayTeam")
    val awayTeam: String,
    @SerializedName("AwayTeamScore")
    val awayTeamScore: Int,
    @SerializedName("DateUtc")
    val dateUtc: String,
    @SerializedName("Group")
    val group: String?,
    @SerializedName("HomeTeam")
    val homeTeam: String,
    @SerializedName("HomeTeamScore")
    val homeTeamScore: Int,
    @SerializedName("Location")
    val location: String,
    @SerializedName("MatchNumber")
    val matchNumber: Int,
    @SerializedName("RoundNumber")
    val roundNumber: Int
)