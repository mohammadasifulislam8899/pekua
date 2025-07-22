package com.droidnest.studio.pekualatest.domain.use_cases.read_onboarding

import com.droidnest.studio.pekualatest.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: DataStoreRepository
) {
     operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}