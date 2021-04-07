package com.fgdc.googlemapstest.data.datasource.remote.services

import com.fgdc.googlemapstest.data.datasource.remote.responses.MapResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapsApi {
    companion object {
        private const val MAP_ENDPOINT =
            "routers/{city}/resources"
    }

    @GET(MAP_ENDPOINT)
    suspend fun getMapInfo(
        @Path("city") city: String,
        @Query("lowerLeftLatLon") lowerLeftLatLon: String,
        @Query("upperRightLatLon") upperRightLatLon: String
    ): Response<List<MapResponseItem>>
}
