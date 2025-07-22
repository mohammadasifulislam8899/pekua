package com.droidnest.studio.pekualatest.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _places = MutableStateFlow<List<PlaceModel>>(emptyList())
    val places = _places.asStateFlow()

    private val _banners = MutableStateFlow<List<String>>(emptyList())
    val banners = _banners.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isBannerLoading = MutableStateFlow(false)
    val isBannerLoading = _isBannerLoading.asStateFlow()

    private val _isPlaceLoading = MutableStateFlow(false)
    val isPlaceLoading = _isPlaceLoading.asStateFlow()

    init {
        fetchCategories()
        fetchBanners()
        fetchPlaces()
    }

    private fun fetchPlaces() {
        viewModelScope.launch {
            _isPlaceLoading.value = true
            val fetchedPlaces = useCases.getPlaceUseCase()
            Log.d("HomeViewModel", "fetchPlaces: $fetchedPlaces")  // Already ase apnar code e
            _places.value = fetchedPlaces.toList() // Ensure nnotun list assign kora hocche
            _isPlaceLoading.value = false
        }
    }
    private fun fetchCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            _categories.value = useCases.getCategoriesUseCase()
            _isLoading.value = false
        }
    }

    private fun fetchBanners() {
        viewModelScope.launch {
            _isBannerLoading.value = true
            val bannersResult = useCases.getBannersUseCase()
            _banners.value = bannersResult // ✅ fix
            _isBannerLoading.value = false
        }
    }
}
