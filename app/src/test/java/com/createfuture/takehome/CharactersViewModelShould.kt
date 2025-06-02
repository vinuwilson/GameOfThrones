package com.createfuture.takehome

import com.createfuture.takehome.domain.models.CharacterModel
import com.createfuture.takehome.domain.usecase.GetCharactersList
import com.createfuture.takehome.presenter.home.CharacterState
import com.createfuture.takehome.presenter.home.CharactersViewModel
import com.createfuture.takehome.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CharactersViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: CharactersViewModel
    private val getCharacterList : GetCharactersList = mock()
    private val charactersList : List<CharacterModel> = mock()
    private val expected = CharacterState(false, charactersList)
    private val exception = CharacterState()

    @Test
    fun `get characters list should invoke at least once`() = runTest{

        mockSuccessfulCase()

        viewModel.characters.first()

        verify(getCharacterList, times(1)).getCharactersList()
    }

    @Test
    fun `emit characters list received from use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.characters.first())
    }

    @Test
    fun `emit error when receive error from use case`() = runTest {

        mockFailureCase()

        assertEquals(exception, viewModel.characters.first())
    }

    private fun mockSuccessfulCase() {

        whenever(getCharacterList.getCharactersList()).thenReturn(
            flow {
                emit(Result.success(charactersList))
            }
        )

        viewModel = CharactersViewModel(getCharacterList)
    }

    private fun mockFailureCase() {

        whenever(getCharacterList.getCharactersList()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        viewModel = CharactersViewModel(getCharacterList)
    }
}