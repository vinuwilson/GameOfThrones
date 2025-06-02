package com.createfuture.takehome.domain.usecase

import com.createfuture.takehome.domain.models.CharacterModel
import com.createfuture.takehome.domain.models.CharactersListMapper
import com.createfuture.takehome.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersList @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val charactersListMapper: CharactersListMapper
) {

    fun getCharactersList(): Flow<Result<List<CharacterModel>>> {
        return flow {
            emit(Result.success(charactersListMapper.invoke(charactersRepository.getCharactersList().getOrNull()!!)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}