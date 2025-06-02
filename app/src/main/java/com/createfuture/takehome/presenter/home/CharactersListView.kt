package com.createfuture.takehome.presenter.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.createfuture.takehome.domain.models.CharacterModel
import com.createfuture.takehome.ui.theme.app_default_text_size
import com.createfuture.takehome.ui.theme.app_large_text_size
import com.createfuture.takehome.ui.theme.app_padding
import com.createfuture.takehome.ui.theme.app_small_padding
import com.createfuture.takehome.ui.theme.divider_thickness

@Composable
fun CharactersListView(
    charactersDetails: CharacterState
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (charactersDetails.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            SingleCharacterView(charactersDetails.characterList)
        }
    }
}

@Composable
fun SingleCharacterView(
    characterList: List<CharacterModel>?
) {
    if (characterList != null) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = app_padding)
        ) {
            items(characterList) { character ->
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .padding(vertical = app_padding)
                        .fillMaxSize()
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = character.name,
                                color = Color.White,
                                fontSize = app_large_text_size
                            )
                            Column {
                                Text(
                                    "Seasons: ",
                                    color = Color.White,
                                    fontSize = app_default_text_size
                                )
                                var seasons = character.tvSeries.joinToString {
                                    when (it) {
                                        "Season 1" -> "I"
                                        "Season 2" -> "II"
                                        "Season 3" -> "III"
                                        "Season 4" -> "IV"
                                        "Season 5" -> "V"
                                        "Season 6" -> "VI"
                                        "Season 7" -> "VII"
                                        "Season 8" -> "VIII"
                                        else -> ""
                                    }
                                }
                                Text(
                                    seasons,
                                    color = Color.Gray,
                                    fontSize = app_default_text_size
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(app_padding))

                        Row {
                            Text(
                                text = "Culture: ",
                                color = Color.White,
                                fontSize = app_default_text_size
                            )
                            Text(
                                text = character.culture,
                                color = Color.Gray,
                                fontSize = app_default_text_size
                            )
                        }
                        Spacer(modifier = Modifier.size(app_small_padding))
                        Row {
                            Text(
                                "Born: ",
                                color = Color.White,
                                fontSize = app_default_text_size
                            )
                            Text(
                                text = character.born,
                                color = Color.Gray,
                                fontSize = app_default_text_size
                            )
                        }
                        Spacer(modifier = Modifier.size(app_small_padding))
                        Row {
                            Text(
                                text = "Died: ",
                                color = Color.White,
                                fontSize = app_default_text_size
                            )
                            Text(
                                text = if (character.died.isEmpty()) "Still Alive" else character.died,
                                color = Color.Gray,
                                fontSize = app_default_text_size
                            )
                        }
                        Spacer(modifier = Modifier.height(app_small_padding))

                        HorizontalDivider(thickness = divider_thickness, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun SingleCharacterViewPreview() {
    SingleCharacterView(
        listOf(
            CharacterModel(
                name = "Eddard Stark",
                gender = "Male",
                culture = "Northmen",
                born = "In 263 AC, at Winterfell",
                died = "In 299 AC, at Great Sept of Baelor in King's Landing",
                aliases = listOf(
                    "Ned",
                    "The Ned",
                    "The Quiet Wolf"
                ),
                tvSeries = listOf(
                    "Season 1",
                    "Season 6"
                ),
                playedBy = listOf(
                    "Sean Bean",
                    "Sebastian Croft",
                    "Robert Aramayo"
                )
            )
        )
    )
}