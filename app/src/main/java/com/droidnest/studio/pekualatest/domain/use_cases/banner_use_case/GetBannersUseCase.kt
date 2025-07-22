package com.droidnest.studio.pekualatest.domain.use_cases.banner_use_case

import com.droidnest.studio.pekualatest.domain.repository.HomeRepository

class GetBannersUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<String> = repository.getBanner()
}