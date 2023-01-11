package com.marina.premierleaguefixtures.data.mapper

import com.marina.premierleaguefixtures.data.local.model.MatchDB
import com.marina.premierleaguefixtures.data.remote.dto.MatchDto
import com.marina.premierleaguefixtures.domain.entity.Match

fun MatchDto.toDomain(): Match {
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

fun List<MatchDto>.toDomain(): List<Match> {
    return map {
        it.toDomain()
    }
}

fun MatchDB.toDomain(): Match {
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

fun List<MatchDB>.fromDBToDomain(): List<Match> {
    return map {
        it.toDomain()
    }
}

fun MatchDto.toDB(): MatchDB {
    return MatchDB(
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

fun List<MatchDto>.fromDtoToDB(): List<MatchDB> {
    return map {
        it.toDB()
    }
}