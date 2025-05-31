package com.createfuture.takehome.presenter.home

import com.createfuture.takehome.data.models.ApiCharacterDto

data class CharacterState(
    val isLoading: Boolean = false,
    val characterList: List<ApiCharacterDto>? = emptyList()
)
