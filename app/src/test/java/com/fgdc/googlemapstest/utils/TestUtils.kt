package com.fgdc.googlemapstest.utils

import com.fgdc.googlemapstest.BuildConfig
import com.fgdc.googlemapstest.data.datasource.remote.responses.MapResponseItem
import com.fgdc.googlemapstest.utils.extensions.empty
import com.google.android.gms.maps.model.LatLng

val DEFAULT_CITY = BuildConfig.MAP_DEFAULT_CITY
val DEFAULT_LOWER_LEFT_LAT_LON: LatLng =
    LatLng(BuildConfig.MAP_DEFAULT_LOWER_LEFT_LAT, BuildConfig.MAP_DEFAULT_LOWER_LEFT_LON)
val UPPER_RIGHT_LAT_LON: LatLng =
    LatLng(BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LAT, BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LON)

fun mockLocations(): List<MapResponseItem> {
    val locationsList = mutableListOf<MapResponseItem>()
    val location = MapResponseItem(
        id = "378:M07",
        name = "PRAÇA DE ESPANHA",
        x = -9.15936,
        y = 38.7377,
        scheduledArrival = 0,
        locationType = 0,
        companyZoneId = 378,
        lat = 38.7377,
        lon = -9.15936,
        allowDropoff = false,
        availableResources = 0,
        batteryLevel = 0,
        bikesAvailable = 0,
        helmets = 0,
        licencePlate = "00000",
        model = "AAA000",
        range = 0,
        realTimeData = false,
        resourceImageId = String.empty(),
        resourceType = String.empty(),
        spacesAvailable = 0,
        station = false
    )
    locationsList.add(location)
    locationsList.add(location)
    locationsList.add(location)

    return locationsList
}
