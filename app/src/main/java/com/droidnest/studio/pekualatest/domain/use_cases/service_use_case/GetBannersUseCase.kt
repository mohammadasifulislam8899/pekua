package com.droidnest.studio.pekualatest.domain.use_cases.service_use_case

import com.droidnest.studio.pekualatest.domain.model.Service
import com.droidnest.studio.pekualatest.domain.repository.HomeRepository

class GetServiceUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<Service> = repository.getService()
}