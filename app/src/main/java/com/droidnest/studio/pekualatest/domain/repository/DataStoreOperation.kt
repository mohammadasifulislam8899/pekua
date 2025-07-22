package com.droidnest.studio.pekualatest.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperation {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}