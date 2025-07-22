package com.droidnest.studio.pekualatest.data.repository

import com.droidnest.studio.pekualatest.domain.repository.DataStoreOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStoreOperation
) {
    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }
}