package com.fgdc.googlemapstest.di.module

import android.content.Context
import com.fgdc.googlemapstest.BuildConfig
import com.fgdc.googlemapstest.data.datasource.remote.services.MapsApi
import com.fgdc.googlemapstest.data.datasource.remote.services.MapsService
import com.fgdc.googlemapstest.utils.helpers.NetworkHandler
import com.fgdc.googlemapstest.utils.helpers.NetworkHandlerImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideMapsApi(retrofit: Retrofit): MapsApi = retrofit.create(MapsApi::class.java)

    @Provides
    fun provideMapsService(mapsApi: MapsApi) = MapsService(mapsApi)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(logging)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .client(okHttpClient)
            .baseUrl(BuildConfig.MAP_API_BASE_URL)
            .build()
    }

    @Provides
    fun providesNetworkHandler(context: Context): NetworkHandler = NetworkHandlerImpl(context)
}
