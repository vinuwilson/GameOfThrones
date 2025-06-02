package com.createfuture.takehome.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.takehome.domain.usecase.GetCharactersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersList: GetCharactersList
) : ViewModel() {

    private var _characters = MutableStateFlow(CharacterState())
    val characters = _characters.asStateFlow()

    init {
        getCharactersList()
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            _characters.update {
                it.copy(
                    isLoading = true
                )
            }
            _characters.update {
                it.copy(
                    isLoading = false,
                    characterList = getCharactersList.getCharactersList().first().getOrDefault(emptyList())
                )
            }
        }
    }

}