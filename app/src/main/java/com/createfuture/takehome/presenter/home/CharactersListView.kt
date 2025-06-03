package com.createfuture.takehome.presenter.home

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.createfuture.takehome.R
import com.createfuture.takehome.domain.models.CharacterModel
import com.createfuture.takehome.ui.theme.app_default_padding
import com.createfuture.takehome.ui.theme.app_default_text_size
import com.createfuture.takehome.ui.theme.app_large_padding
import com.createfuture.takehome.ui.theme.app_large_text_size
import com.createfuture.takehome.ui.theme.app_medium_padding
import com.createfuture.takehome.ui.theme.app_small_padding
import com.createfuture.takehome.ui.theme.divider_thickness
import com.createfuture.takehome.ui.theme.searchbar_corner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListView(
    charactersDetails: CharacterState,
    onSearch: (String) -> List<CharacterModel>
) {
    var searchQuery by remember { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier.padding(app_medium_padding),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = { expanded = false },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text(stringResource(R.string.search_placeholder)) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedPlaceholderColor = Color.White,
                            focusedPlaceholderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                shape = RoundedCornerShape(searchbar_corner),
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Gray,
                    dividerColor = Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = app_large_padding)
                        .semantics { isTraversalGroup = true }
                        .paint(
                            painterResource(R.drawable.img_characters)
                        )
                ) {
                    val filteredList = onSearch(searchQuery)
                    SingleCharacterView(filteredList)
                }

            }
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = innerPadding.calculateTopPadding(),
                    horizontal = app_medium_padding
                ),
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

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleCharacterView(
    characterList: List<CharacterModel>?
) {
    val context = LocalContext.current

    if (characterList != null && characterList.isNotEmpty()) {
        LazyColumn {
            items(characterList) { character ->
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .padding(vertical = app_default_padding)
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
                                    stringResource(R.string.seasons),
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
                        Spacer(modifier = Modifier.size(app_default_padding))

                        Row {
                            Text(
                                stringResource(R.string.culture),
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
                                stringResource(R.string.born),
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
                                stringResource(R.string.died),
                                color = Color.White,
                                fontSize = app_default_text_size
                            )
                            Text(
                                text = if (character.died.isEmpty()) stringResource(R.string.still_alive) else character.died,
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
    } else {
        LaunchedEffect(Unit) {
            Toast.makeText(
                context,
                R.string.error_message,
                Toast.LENGTH_SHORT
            ).show()
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