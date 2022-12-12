package com.marina.premierleaguefixtures.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val group: String?,
    val homeTeamScore: Int,
    val awayTeamScore: Int
) : Parcelable
