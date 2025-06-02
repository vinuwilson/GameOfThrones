package com.createfuture.takehome.presenter.home

import com.createfuture.takehome.domain.models.CharacterModel

data class CharacterState(
    val isLoading: Boolean = false,
    val characterList: List<CharacterModel>? = emptyList()
)
