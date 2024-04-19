package com.example.home.presentation.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.component.AppLoading
import com.example.core.ui.component.PixabayInfoDialog
import com.example.home.presentation.components.ImageCardItem
import com.example.utils.model.Hit
import com.example.utils.core.ActionState
import com.example.utils.core.UiState
import com.example.utils.core.toJsonString


@Composable
internal fun HomeSearchRoute(
    onImageClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchViewModel: HomeSearchViewModel = hiltViewModel(),
) {

    val searchResultUiState by searchViewModel.searchResultUiState.collectAsStateWithLifecycle()
    val actionState by searchViewModel.actionState.collectAsStateWithLifecycle()
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()


    HomeSearchScreen(
        onImageClick = onImageClick,
        modifier = modifier,
        actionState = actionState,
        searchQuery= searchQuery,
        searchResultUiState = searchResultUiState,
        onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
        onActionStateChanged = searchViewModel::onActionStateChanged
    )
}

@Composable
fun HomeSearchScreen(
    onImageClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    actionState: ActionState,
    searchQuery: String,
    searchResultUiState: UiState<List<Hit>?>,
    onSearchQueryChanged: (String?) -> Unit = {},
    onActionStateChanged: (ActionState) -> Unit = {},


    ) {
    var searchText by remember { mutableStateOf(searchQuery) }
//    val images by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchQueryChanged(it)
            },
            label = { Text("Enter search keyword") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                // Perform search operation here
                onSearchQueryChanged(searchText)
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )



        when (searchResultUiState) {

            is UiState.Error -> {
                searchResultUiState.throwable?.printStackTrace()
                Log.d("xxx", "Errorrrr")

            }

            is UiState.Ideal -> {
                Log.d("xxx", "Ideal View")

            }

            is UiState.Loading -> {
                AppLoading(isLoading = searchResultUiState.isLoading)
            }

            is UiState.Success -> {
                Log.d("xxx", "Success")

                searchResultUiState.data?.let {
                    SearchResultView(
                        onImageClick= onImageClick,
                        actionState = actionState,
                        imagesList = it,
                        onActionStateChanged = onActionStateChanged
                    )
                } ?: run {
                    Log.d("xxx", "Empty Ideal View")
                }
            }

        }


    }
}

@Composable
internal fun SearchResultView(
    onImageClick: (String) -> Unit,
    actionState: ActionState,
    imagesList: List<Hit>,
    onActionStateChanged: (ActionState) -> Unit = {},
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2), // Set number of columns

    ) {
        items(items = imagesList) { imageHit ->
            ImageCardItem(imageHit= imageHit, openDetails = {
                onActionStateChanged(ActionState.ACTION)
            })
            when (actionState) {
                ActionState.NONE -> {
                    PixabayInfoDialog(
                        visibility = false,
                        goToImageDetails = {},
                        onCancel = {})
                }
                ActionState.ACTION -> {
                    PixabayInfoDialog(
                        goToImageDetails = {
                            onImageClick(imageHit.toJsonString())
                            Log.d("hhhhh", "${imageHit.user}")
                            onActionStateChanged(ActionState.NONE)
                        },
                        onCancel = { onActionStateChanged(ActionState.NONE) }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewImageSearchScreen() {
}
