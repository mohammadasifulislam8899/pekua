package com.droidnest.studio.pekualatest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidnest.studio.pekualatest.domain.model.Service
import com.droidnest.studio.pekualatest.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchServices()
    }

    fun fetchServices() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = useCases.getServiceUseCase()
                _services.value = result
            } catch (e: Exception) {
                _services.value = emptyList() // optional: handle error UI
            } finally {
                _isLoading.value = false
            }
        }
    }
}