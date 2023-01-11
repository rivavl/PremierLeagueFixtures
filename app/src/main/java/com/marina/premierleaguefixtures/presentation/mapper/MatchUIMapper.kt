package com.marina.premierleaguefixtures.presentation.mapper

import com.marina.premierleaguefixtures.domain.entity.Match
import com.marina.premierleaguefixtures.presentation.entity.MatchUI

fun Match.toUI(): MatchUI {
    return MatchUI(
        matchNumber = matchNumber,
        roundNumber = roundNumber,
        dateUtc = dateUtc,
        location = location,
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        group = group,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore,
        homeWins = homeTeamScore > awayTeamScore,
        awayWins = awayTeamScore > homeTeamScore
    )
}

fun List<Match>.toUI(): List<MatchUI> {
    return map {
        it.toUI()
    }
}