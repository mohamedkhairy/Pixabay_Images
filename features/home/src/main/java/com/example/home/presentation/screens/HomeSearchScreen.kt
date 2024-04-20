package com.example.home.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.component.AppLoading
import com.example.core.ui.component.PixabayInfoDialog
import com.example.home.presentation.components.ImageCardItem
import com.example.utils.core.ActionState
import com.example.utils.core.UiState
import com.example.utils.core.toJsonString
import com.example.utils.model.Hit


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
        searchQuery = searchQuery,
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
                ViewStateMessage("can't find any result")
            }

            is UiState.Ideal -> {
                ViewStateMessage("Please, enter at least 2 chars")
            }

            is UiState.Loading -> {
                AppLoading(isLoading = searchResultUiState.isLoading)
            }

            is UiState.Success -> {
                if (searchResultUiState.data.isNullOrEmpty()) {
                    ViewStateMessage("can't find any result")
                } else {
                    SearchResultView(
                        onImageClick = onImageClick,
                        actionState = actionState,
                        imagesList = searchResultUiState.data!!,
                        onActionStateChanged = onActionStateChanged
                    )

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
            ImageCardItem(imageHit = imageHit, openDetails = {
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

@Composable
fun ViewStateMessage(msg: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = msg,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun PreviewImageSearchScreen() {
}
