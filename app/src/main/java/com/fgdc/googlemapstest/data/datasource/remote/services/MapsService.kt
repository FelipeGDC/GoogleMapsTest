package com.fgdc.googlemapstest.data.datasource.remote.services

import com.fgdc.googlemapstest.data.datasource.remote.responses.MapResponseItem
import com.google.android.gms.maps.model.LatLng
import retrofit2.Response
import javax.inject.Inject

class MapsService @Inject constructor(private val mapsApi: MapsApi) : MapsApi {
    override suspend fun getMapInfo(
        city: String,
        lowerLeftLatLon: String,
        upperRightLatLon: String
    ): Response<List<MapResponseItem>> = mapsApi.getMapInfo(city, lowerLeftLatLon, upperRightLatLon)
}
