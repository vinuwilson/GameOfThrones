package com.createfuture.takehome.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeHomeFragment : Fragment() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView = ComposeView(requireContext()).apply {
        setContent {
            val viewModel = hiltViewModel<CharactersViewModel>()
            val state = viewModel.characters.collectAsStateWithLifecycle()

            CharactersListView(
                charactersDetails = state.value
            ) { searchValue ->
                viewModel.onSearch(searchValue)
            }
        }
    }
}