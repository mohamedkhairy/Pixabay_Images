package com.example.home.presentation.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.model.Hit
import com.example.home.domain.useCase.ImageSearchUseCase
import com.example.utils.core.ActionState
import com.example.utils.core.UiState
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

//    private val _hitList = MutableStateFlow<ArrayList<Hit>>(arrayListOf())
//    val hitList: StateFlow<List<Hit>> = _hitList


//    private val _uiState = MutableStateFlow(GameUiState())
//    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

//    var loading by mutableStateOf(false)
//        private set
//        get
//
//    var isError by mutableStateOf(false)
//        private set
//        get


//    val loading: MutableState<Boolean> = mutableStateOf(false)
//    val isError: MutableState<Boolean> = mutableStateOf(false)
//    val searchKey: MutableState<String> = mutableStateOf("fruits")


//    init {
//        getImageBySearchTag()
//    }

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


//    private fun getImageBySearchTag() {
//        viewModelScope.launch(Dispatchers.Main) {
//            imageSearchUseCase(searchQuery.value)
//                .collect { result ->
//                    when (result) {
//                        is DataState.Loading -> {
//                            loading.value = true
//                        }
//
//                        is DataState.Success -> {
//                            loading = false
//                            _hitList.value.clear()
//                            result.data?.let {
//                                isError = false
//                                _hitList.value.addAll(it)
//                            } ?: run {
//                                isError = false
//                            }
//
//                        }
//
//                        is DataState.Error -> {
//                            loading = false
//                            isError = true
//                        }
//
//                        is DataState.Ideal -> {
////                            loading.value = false
////                            isError.value = true
//                        }
//                    }
//                }
//
//
//        }
//
//    }


    fun onSearchQueryChanged(query: String?) {
        savedStateHandle[SEARCH_QUERY] = query
//        getImageBySearchTag()
    }

    fun onActionStateChanged(actionState: ActionState) {
        savedStateHandle[ACTION] = actionState
    }
//    fun setSearchKey(searchKey: String) {
//        this.searchQuery.value = searchKey
//        getImageBySearchTag()
//    }

}

private const val SEARCH_QUERY_MIN_LENGTH = 2
private const val SEARCH_QUERY = "searchQuery"
private const val ACTION = "action_state"