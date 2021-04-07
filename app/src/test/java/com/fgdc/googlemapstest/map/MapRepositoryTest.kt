package com.fgdc.googlemapstest.map

import com.fgdc.googlemapstest.data.datasource.remote.services.MapsService
import com.fgdc.googlemapstest.data.repositories.MapRepositoryImpl
import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.utils.DEFAULT_CITY
import com.fgdc.googlemapstest.utils.DEFAULT_LOWER_LEFT_LAT_LON
import com.fgdc.googlemapstest.utils.UPPER_RIGHT_LAT_LON
import com.fgdc.googlemapstest.utils.functional.State
import com.fgdc.googlemapstest.utils.functional.Success
import com.fgdc.googlemapstest.utils.helpers.NetworkHandler
import com.fgdc.googlemapstest.utils.mockLocations
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class MapRepositoryTest {

    @Test
    fun `should get map locations from service on success`(): Unit = runBlocking {

        val mockLocations = mockLocations()

        val service = mock<MapsService> {
            onBlocking {
                getMapInfo(
                    DEFAULT_CITY,
                    "${DEFAULT_LOWER_LEFT_LAT_LON.latitude},${DEFAULT_LOWER_LEFT_LAT_LON.longitude}",
                    "${UPPER_RIGHT_LAT_LON.latitude},${UPPER_RIGHT_LAT_LON.longitude}",
                )
            } doReturn Response.success(mockLocations)
        }
        service.getMapInfo(
            DEFAULT_CITY,
            "${DEFAULT_LOWER_LEFT_LAT_LON.latitude},${DEFAULT_LOWER_LEFT_LAT_LON.longitude}",
            "${UPPER_RIGHT_LAT_LON.latitude},${UPPER_RIGHT_LAT_LON.longitude}",
        ).body() shouldBeEqualTo mockLocations

        val networkHandler = mock<NetworkHandler> {
            onBlocking { isConnected } doReturn true
        }
        val repository = MapRepositoryImpl(
            service,
            networkHandler
        )
        val flow: Flow<State<List<MapItemDomain>>> =
            repository.getMapInfo(
                DEFAULT_CITY,
                DEFAULT_LOWER_LEFT_LAT_LON,
                UPPER_RIGHT_LAT_LON,
            )
        flow.collect { result ->
            result.`should be instance of`<Success<List<MapItemDomain>>>()
            when (result) {
                is Success<List<MapItemDomain>> -> {
                    result.data shouldBeEqualTo mockLocations.map { it.toMapDomain() }
                }
            }
        }
    }
}
