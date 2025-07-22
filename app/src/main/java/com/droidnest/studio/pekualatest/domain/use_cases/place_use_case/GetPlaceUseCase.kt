package com.droidnest.studio.pekualatest.domain.use_cases.place_use_case

import com.droidnest.studio.pekualatest.domain.model.PlaceModel
import com.droidnest.studio.pekualatest.domain.repository.HomeRepository

class GetPlaceUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<PlaceModel> = repository.getPlace()
}