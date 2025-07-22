package com.droidnest.studio.pekualatest.data.repository

import android.util.Log
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.domain.model.Service
import com.droidnest.studio.pekualatest.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeRepository {

    override suspend fun getAllCategories(): List<Category> {
        return try {
            val result = firestore.collection("services").get().await()

            result.documents
                .mapNotNull { doc ->
                    val type = doc.getString("type")?.lowercase()
                    val imageUrl = doc.getString("imageUrl")
                    if (type != null && imageUrl != null) {
                        Category(type, imageUrl)
                    } else null
                }
                // unique types er jonno distinctBy type
                .distinctBy { it.type }
                // alphabetically sorted by type
                .sortedBy { it.type }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getService(): List<Service> {
        return try {
            val result = firestore.collection("services").get().await()
            result.documents.mapNotNull { doc ->
                doc.toObject(Service::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPlace(): List<PlaceModel> {
        return try {
            val result = firestore.collection("places").get().await()
            val placesList = result.documents.mapNotNull { doc ->
                doc.toObject(PlaceModel::class.java)
            }
            placesList.forEach {
                Log.d("GetPlaceUseCase", "Place: $it")
            }
            placesList
        } catch (e: Exception) {
            emptyList()
        }
    }


    override suspend fun getBanner(): List<String> {
        return try {
            val result = firestore.collection("banners").get().await()
            val urls = result.documents.mapNotNull { doc ->
                val url = doc.getString("url")
                url
            }
            urls
        } catch (e: Exception) {
            emptyList()
        }
    }

}
