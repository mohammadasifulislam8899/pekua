package com.droidnest.studio.pekualatest.di

import android.content.Context
import com.droidnest.studio.pekualatest.data.repository.DataStoreOperationsImpl
import com.droidnest.studio.pekualatest.data.repository.DataStoreRepository
import com.droidnest.studio.pekualatest.data.repository.HomeRepositoryImpl
import com.droidnest.studio.pekualatest.domain.repository.DataStoreOperation
import com.droidnest.studio.pekualatest.domain.repository.HomeRepository
import com.droidnest.studio.pekualatest.domain.use_cases.UseCases
import com.droidnest.studio.pekualatest.domain.use_cases.banner_use_case.GetBannersUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.category_use_cases.GetCategoriesUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.place_use_case.GetPlaceUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.droidnest.studio.pekualatest.domain.use_cases.service_use_case.GetServiceUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideServiceRepository(firestore: FirebaseFirestore): HomeRepository =
        HomeRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStoreOperation: DataStoreOperation
    ): DataStoreRepository =
        DataStoreRepository(dataStoreOperation)

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperation {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        repository: HomeRepository,
        dataStoreRepository: DataStoreRepository
    ): UseCases {
        return UseCases(
            getCategoriesUseCase = GetCategoriesUseCase(repository),
            getBannersUseCase = GetBannersUseCase(repository),
            getServiceUseCase = GetServiceUseCase(repository),
            getPlaceUseCase = GetPlaceUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(dataStoreRepository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(dataStoreRepository)
        )
    }

}
