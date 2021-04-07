package com.fgdc.googlemapstest.di.module

import com.fgdc.googlemapstest.data.datasource.remote.services.MapsApi
import com.fgdc.googlemapstest.data.repositories.MapRepository
import com.fgdc.googlemapstest.data.repositories.MapRepositoryImpl
import com.fgdc.googlemapstest.utils.helpers.NetworkHandler
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideMapRepository(
        mapsApi: MapsApi,
        networkHandler: NetworkHandler
    ): MapRepository = MapRepositoryImpl(mapsApi, networkHandler)
}
