package com.createfuture.takehome

import com.createfuture.takehome.data.api.APICharacters
import com.createfuture.takehome.data.models.ApiCharacterDto
import com.createfuture.takehome.data.repository.CharactersService
import com.createfuture.takehome.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CharactersServiceShould : BaseUnitTest() {

    private lateinit var charactersService: CharactersService
    private val apiCharacters: APICharacters = mock()
    private val charactersList: List<ApiCharacterDto> = mock()
    private val expected = Result.success(charactersList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `invoke characters api to at least once`() = runTest {

        mockSuccessfulCase()

        charactersService.getCharactersList()

        verify(apiCharacters, times(1)).getCharacters()
    }

    @Test
    fun `emit character list from api`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, charactersService.getCharactersList())
    }

    @Test
    fun `emit error when api fails`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, charactersService.getCharactersList().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {

        whenever(apiCharacters.getCharacters()).thenReturn(charactersList)

        charactersService = CharactersService(apiCharacters)
    }

    private suspend fun mockFailureCase() {

        whenever(apiCharacters.getCharacters()).thenThrow(RuntimeException("Damn back end exception"))

        charactersService = CharactersService(apiCharacters)
    }
}