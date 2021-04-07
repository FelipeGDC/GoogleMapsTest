package com.fgdc.googlemapstest.di.module

import com.fgdc.googlemapstest.data.repositories.MapRepository
import com.fgdc.googlemapstest.domain.usecases.GetMapInfoByCoordinatesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMapInfoByCoordinatesUseCase(mapRepository: MapRepository) =
        GetMapInfoByCoordinatesUseCase(mapRepository)
}
