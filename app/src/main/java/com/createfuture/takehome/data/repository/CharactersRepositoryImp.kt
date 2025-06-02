package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.models.ApiCharacterDto
import com.createfuture.takehome.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImp @Inject constructor(
    private val charactersService: CharactersService
) : CharactersRepository {

    override suspend fun getCharactersList(): Result<List<ApiCharacterDto>> {
        return charactersService.getCharactersList()
    }
}