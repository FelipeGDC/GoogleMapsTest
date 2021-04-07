package com.fgdc.googlemapstest.ui.map.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.domain.usecases.GetMapInfoByCoordinatesUseCase
import com.fgdc.googlemapstest.ui.base.BaseViewModel
import com.fgdc.googlemapstest.ui.map.models.MapItemView
import com.fgdc.googlemapstest.utils.functional.Error
import com.fgdc.googlemapstest.utils.functional.ErrorNoConnection
import com.fgdc.googlemapstest.utils.functional.Success
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getMapInfoByCoordinatesUseCase: GetMapInfoByCoordinatesUseCase
) : BaseViewModel() {

    var mapPositions = MutableLiveData<List<MapItemView>>()

    fun getLocationInfo(city: String, lowerLeftLatLon: LatLng, upperRightLatLon: LatLng) {
        viewModelScope.launch {
            getMapInfoByCoordinatesUseCase(
                GetMapInfoByCoordinatesUseCase.Params(
                    city,
                    lowerLeftLatLon,
                    upperRightLatLon,
                )
            ).onStart { }
                .onStart { /*Start loading animation*/ }
                .onStart { /*Finish loading animation*/ }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<List<MapItemDomain>> -> handleSuccessGetInfoLocation(result.data)
                        is Error -> handleFailure(result.exception)
                        is ErrorNoConnection -> handleFailure(result.exception)
                    }
                }
        }
    }

    private fun handleSuccessGetInfoLocation(data: List<MapItemDomain>) {
        mapPositions.postValue(data.map { it.toMapView() })
    }
}
