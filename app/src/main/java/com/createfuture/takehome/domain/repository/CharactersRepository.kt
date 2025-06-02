package com.createfuture.takehome.domain.repository

import com.createfuture.takehome.data.models.ApiCharacterDto

interface CharactersRepository {
    suspend fun getCharactersList() : Result<List<ApiCharacterDto>>
}