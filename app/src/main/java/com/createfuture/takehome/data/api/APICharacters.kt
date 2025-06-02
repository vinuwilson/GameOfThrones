package com.createfuture.takehome.data.api

import com.createfuture.takehome.data.models.ApiCharacterDto
import retrofit2.http.GET

interface APICharacters {
    @GET("characters")
    suspend fun getCharacters(): List<ApiCharacterDto>
}