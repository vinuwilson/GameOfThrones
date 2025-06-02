package com.createfuture.takehome

import com.createfuture.takehome.data.models.ApiCharacterDto
import com.createfuture.takehome.domain.models.CharacterModel
import com.createfuture.takehome.domain.models.CharactersListMapper
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.domain.usecase.GetCharactersList
import com.createfuture.takehome.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCharactersListShould : BaseUnitTest() {

    private lateinit var getCharacterList : GetCharactersList
    private val charactersRepository : CharactersRepository = mock()
    private val charactersListMapper: CharactersListMapper = mock()
    private val charactersList : List<ApiCharacterDto> = mock()
    private val characterModel : List<CharacterModel> = mock()
    private val expected = Result.success(characterModel)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `repository should invoke at least once`() = runTest {

        mockSuccessfulCase()

        getCharacterList.getCharactersList().first()

        verify(charactersRepository, times(1)).getCharactersList()
    }

    @Test
    fun `convert characters list into flow and emit`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getCharacterList.getCharactersList().first())
    }

    @Test
    fun `emit error when receive from repository`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, getCharacterList.getCharactersList().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {

        whenever(charactersRepository.getCharactersList()).thenReturn(
            Result.success(charactersList)
        )

        whenever(charactersListMapper.invoke(charactersList)).thenReturn(characterModel)

        getCharacterList = GetCharactersList(charactersRepository, charactersListMapper)
    }

    private suspend fun mockFailureCase() {

        whenever(charactersRepository.getCharactersList()).thenReturn(
            Result.failure(RuntimeException("Something went wrong"))
        )

        getCharacterList = GetCharactersList(charactersRepository, charactersListMapper)
    }
}