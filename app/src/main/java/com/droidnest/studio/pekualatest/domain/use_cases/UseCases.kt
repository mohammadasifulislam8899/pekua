package com.droidnest.studio.pekualatest.domain.use_cases

import com.droidnest.studio.pekualatest.domain.use_cases.banner_use_case.GetBannersUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.category_use_cases.GetCategoriesUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.place_use_case.GetPlaceUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.service_use_case.GetServiceUseCase

data class UseCases(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getBannersUseCase: GetBannersUseCase,
    val getServiceUseCase: GetServiceUseCase,
    val getPlaceUseCase: GetPlaceUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    )
