package com.example.utils.core


//sealed interface UiState {
//
//
//    data class Error(
//        val error: Throwable? = null
//    ): UiState
//
//    data class Success<T>(
//        val data: T? = null
//    ): UiState
//
//    data object Loading : UiState
//
//    data object Ideal : UiState
//
//    companion object {
//        fun <T> success(data: T): UiState = Success(data)
//    }
//}


sealed class UiState<T> {


    data class Error<T>(
        val throwable: Throwable? = null
    ) : UiState<T>()

    data class Success<T>(
        val data: T? = null
    ) : UiState<T>()

    data class Loading<T>(
        var isLoading: Boolean = false
    ) : UiState<T>()

    class Ideal<T> : UiState<T>()


    companion object {


        fun <T> success(data: T): UiState<T> = Success(data)
    }
}

