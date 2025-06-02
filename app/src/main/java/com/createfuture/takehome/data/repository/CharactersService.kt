package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.api.APICharacters
import com.createfuture.takehome.data.models.ApiCharacterDto
import javax.inject.Inject

class CharactersService @Inject constructor(
    private val apiCharacters: APICharacters
) {
    suspend fun getCharactersList(): Result<List<ApiCharacterDto>> {
        return try {
            Result.success(apiCharacters.getCharacters())
        } catch (_: Exception) {
            Result.failure(RuntimeException("Something went wrong"))
        }
    }
}