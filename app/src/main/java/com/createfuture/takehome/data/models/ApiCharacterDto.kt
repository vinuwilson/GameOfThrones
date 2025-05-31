package com.createfuture.takehome.data.models

import com.createfuture.takehome.domain.models.CharacterModel

data class ApiCharacterDto(
    val name: String = "",
    val gender: String = "",
    val culture: String = "",
    val born: String = "",
    val died: String = "",
    val aliases: List<String> = listOf(),
    val tvSeries: List<String> = listOf(),
    val playedBy: List<String> = listOf(),
)

fun ApiCharacterDto.toCharacterModel() = CharacterModel(
    name = name,
    gender = gender,
    culture = culture,
    born = born,
    died = died,
    aliases = aliases,
    tvSeries = tvSeries,
    playedBy = playedBy
)