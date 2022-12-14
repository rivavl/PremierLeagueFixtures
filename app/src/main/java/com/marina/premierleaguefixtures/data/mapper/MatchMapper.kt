package com.marina.premierleaguefixtures.data.mapper

import com.marina.premierleaguefixtures.data.remote.dto.MatchDto
import com.marina.premierleaguefixtures.model.Match

fun MatchDto.toUI(): Match {
    return Match(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        dateUtc = dateUtc,
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        group = group,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore
    )
}

fun List<MatchDto>.toUI(): List<Match> {
    return map {
        it.toUI()
    }
}