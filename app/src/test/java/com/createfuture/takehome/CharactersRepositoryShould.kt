package com.createfuture.takehome

import com.createfuture.takehome.data.models.ApiCharacterDto
import com.createfuture.takehome.data.repository.CharactersRepositoryImp
import com.createfuture.takehome.data.repository.CharactersService
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CharactersRepositoryShould : BaseUnitTest() {

    private lateinit var charactersRepository : CharactersRepository
    private val charactersService: CharactersService = mock()
    private val charactersList : List<ApiCharacterDto> = mock()
    private val expected = Result.success(charactersList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `service should invoke at least once`() = runTest {

        mockSuccessfulCase()

        charactersRepository.getCharactersList()

        verify(charactersService, times(1)).getCharactersList()
    }

    @Test
    fun `emit characters list from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, charactersRepository.getCharactersList())
    }

    @Test
    fun `emit error when receive error from service`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, charactersRepository.getCharactersList().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {

        whenever(charactersService.getCharactersList()).thenReturn(
            Result.success(charactersList)
        )

        charactersRepository = CharactersRepositoryImp(charactersService)
    }

    private suspend fun mockFailureCase() {

        whenever(charactersService.getCharactersList()).thenReturn(
            Result.failure(RuntimeException("Something went wrong"))
        )

        charactersRepository = CharactersRepositoryImp(charactersService)
    }
}