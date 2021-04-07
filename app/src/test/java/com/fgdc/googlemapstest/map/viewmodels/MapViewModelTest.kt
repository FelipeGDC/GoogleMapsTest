package com.fgdc.googlemapstest.map.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fgdc.googlemapstest.data.repositories.MapRepositoryImpl
import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.domain.usecases.GetMapInfoByCoordinatesUseCase
import com.fgdc.googlemapstest.ui.map.models.MapItemView
import com.fgdc.googlemapstest.ui.map.viewmodel.MapViewModel
import com.fgdc.googlemapstest.utils.*
import com.fgdc.googlemapstest.utils.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MapViewModelTest {

    private lateinit var viewModel: MapViewModel
    private lateinit var getMapInfoByCoordinatesUseCase: GetMapInfoByCoordinatesUseCase

    private var repository = mock<MapRepositoryImpl>()
    private val mapLocations = mock<Observer<List<MapItemView>>>()
    private val isErrorObserver = mock<Observer<Throwable>>()

    @get:Rule
    var coroutinesRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        getMapInfoByCoordinatesUseCase = GetMapInfoByCoordinatesUseCase(repository)
        viewModel = MapViewModel(getMapInfoByCoordinatesUseCase).apply {
            mapPositions.observeForever(mapLocations)
            failure.observeForever(isErrorObserver)
        }
    }

    @Test
    fun `should emit get map info on success`() =
        coroutinesRule.dispatcher.runBlockingTest {
            val expectedLocations =
                Success(mockLocations().map { it.toMapDomain() })

            val channel = Channel<Success<List<MapItemDomain>>>()
            val flow = channel.consumeAsFlow()

            doReturn(flow)
                .whenever(repository)
                .getMapInfo(
                    DEFAULT_CITY,
                    DEFAULT_LOWER_LEFT_LAT_LON,
                    UPPER_RIGHT_LAT_LON
                )

            launch {
                channel.send(expectedLocations)
                channel.close(IOException())
            }

            viewModel.getLocationInfo(
                DEFAULT_CITY,
                DEFAULT_LOWER_LEFT_LAT_LON,
                UPPER_RIGHT_LAT_LON
            )

            verify(mapLocations).onChanged(expectedLocations.data.map { it.toMapView() })
        }
}
