package com.createfuture.takehome.domain.models

import com.createfuture.takehome.data.models.ApiCharacterDto
import com.createfuture.takehome.data.models.toCharacterModel
import javax.inject.Inject

class CharactersListMapper @Inject constructor(): Function1<List<ApiCharacterDto>, List<CharacterModel>> {
    override fun invoke(charactersList: List<ApiCharacterDto>): List<CharacterModel> {
        return charactersList.map {
            it.toCharacterModel()
        }
    }
}