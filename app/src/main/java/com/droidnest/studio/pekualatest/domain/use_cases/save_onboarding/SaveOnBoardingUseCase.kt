package com.droidnest.studio.pekualatest.domain.use_cases.save_onboarding

import com.droidnest.studio.pekualatest.data.repository.DataStoreRepository


class SaveOnBoardingUseCase(
    private val repository: DataStoreRepository
) {
    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}