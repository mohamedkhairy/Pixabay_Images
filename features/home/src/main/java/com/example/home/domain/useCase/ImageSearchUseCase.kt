package com.example.home.domain.useCase

import com.example.home.domain.entity.SearchKeyValidation
import com.example.home.domain.repository.ImageSearchRepository
import com.example.utils.core.UiState
import com.example.utils.model.Hit
import com.example.utils.usecases.FlowUseCase
import com.example.utils.usecases.obtainOutcome
import com.paymob.pos.utils.dispatchers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageSearchUseCase @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String?, UiState<List<Hit>?>>(ioDispatcher) {


    override suspend fun execute(parameters: String?): Flow<UiState<List<Hit>?>> {
        if (parameters.isNullOrEmpty()) {
            throw SearchKeyValidation()
        }
        return imageSearchRepository.callImageSearch(parameters).obtainOutcome()
    }

}

