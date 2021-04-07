package com.fgdc.googlemapstest.data.repositories

import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.utils.functional.State
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    fun getMapInfo(
        city: String,
        lowerLeftLatLon: LatLng,
        upperRightLatLon: LatLng
    ): Flow<State<List<MapItemDomain>>>
}
