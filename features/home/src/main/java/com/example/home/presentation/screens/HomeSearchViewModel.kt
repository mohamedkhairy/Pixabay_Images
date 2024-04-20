package com.example.home.presentation.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.useCase.ImageSearchUseCase
import com.example.utils.core.ActionState
import com.example.utils.core.UiState
import com.example.utils.model.Hit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeSearchViewModel @Inject constructor(
    private val imageSearchUseCase: ImageSearchUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "fruits")
    val actionState = savedStateHandle.getStateFlow(key = ACTION, initialValue = ActionState.NONE)


    val searchResultUiState: StateFlow<UiState<List<Hit>?>> =
        searchQuery.flatMapLatest { query ->
            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                flowOf(UiState.Ideal())
            } else {
                imageSearchUseCase(query)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Ideal(),
        )


    fun onSearchQueryChanged(query: String?) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onActionStateChanged(actionState: ActionState) {
        savedStateHandle[ACTION] = actionState
    }

}

private const val SEARCH_QUERY_MIN_LENGTH = 2
private const val SEARCH_QUERY = "searchQuery"
private const val ACTION = "action_state"