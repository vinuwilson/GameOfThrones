package com.createfuture.takehome.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.createfuture.takehome.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView = ComposeView(requireContext()).apply {
        setContent {
            val viewModel: CharactersViewModel by viewModels()
            val state = viewModel.characters.collectAsStateWithLifecycle()
            val charactersBody = state.value
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .paint(
                                painterResource(id = R.drawable.img_characters),
                                contentScale = ContentScale.FillBounds
                            )
                            .verticalScroll(rememberScrollState())
                    ) {
                        if (charactersBody.characterList != null) {
                            for (character in charactersBody.characterList) {
                                Row {
                                    Spacer(modifier = Modifier.size(16.dp))
                                    Column {
                                        Text(
                                            text = character.name,
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                        Row {
                                            Text(
                                                text = "Culture: ",
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                            Text(
                                                text = character.culture,
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Row {
                                            Text("Born: ", color = Color.White, fontSize = 16.sp)
                                            Text(
                                                text = character.born,
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = "Died: ",
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                            Text(
                                                text = character.died,
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Column {
                                        Text("Seasons: ", color = Color.White, fontSize = 14.sp)
                                        var seasons = character.tvSeries.joinToString {
                                            when (it) {
                                                "Season 1" -> "I "
                                                "Season 2" -> "II, "
                                                "Season 3" -> "III, "
                                                "Season 4" -> "IV, "
                                                "Season 5" -> "V, "
                                                "Season 6" -> "VI, "
                                                "Season 7" -> "VII, "
                                                "Season 8" -> "VIII"
                                                else -> ""
                                            }
                                        }
                                        Text(seasons, color = Color.White, fontSize = 14.sp)
                                    }
                                }
                                Spacer(modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}