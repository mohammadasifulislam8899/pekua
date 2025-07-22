package com.droidnest.studio.pekualatest.domain.use_cases.category_use_cases

import com.droidnest.studio.pekualatest.domain.model.Category
import com.droidnest.studio.pekualatest.domain.repository.HomeRepository

class GetCategoriesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): List<Category> = repository.getAllCategories()
}