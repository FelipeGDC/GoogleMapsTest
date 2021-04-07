package com.fgdc.googlemapstest.data.repositories

import android.util.Log
import com.fgdc.googlemapstest.data.datasource.remote.services.MapsApi
import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.utils.exception.ErrorHandler
import com.fgdc.googlemapstest.utils.exception.ErrorHandler.NETWORK_ERROR_MESSAGE
import com.fgdc.googlemapstest.utils.functional.*
import com.fgdc.googlemapstest.utils.helpers.NetworkHandler
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val apiService: MapsApi,
    private val networkHandler: NetworkHandler
) : MapRepository {
    override fun getMapInfo(
        city: String,
        lowerLeftLatLon: LatLng,
        upperRightLatLon: LatLng
    ): Flow<State<List<MapItemDomain>>> = flow {
        emit(
            if (networkHandler.isConnected) {
                apiService.getMapInfo(
                    city,
                    "${lowerLeftLatLon.latitude},${lowerLeftLatLon.longitude}",
                    "${upperRightLatLon.latitude},${upperRightLatLon.longitude}"
                ).run {
                    if (isSuccessful && body() != null) {
                        Success(body()!!.map { it.toMapDomain() })
                    } else {
                        BadRequest(Throwable(ErrorHandler.BAD_REQUEST))
                    }
                }
            } else {
                ErrorNoConnection(Throwable(NETWORK_ERROR_MESSAGE))
            }
        )
    }.catch {
        it.printStackTrace()
        emit(Error(Throwable(ErrorHandler.UNKNOWN_ERROR)))
    }.flowOn(Dispatchers.IO)
}
