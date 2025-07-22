package com.droidnest.studio.pekualatest.domain.repository

import androidx.datastore.dataStore
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.domain.model.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeRepository {
    suspend fun getAllCategories(): List<Category>
    suspend fun getBanner() : List<String>
    suspend fun getService() : List<Service>
    suspend fun getPlace() : List<PlaceModel>
}
