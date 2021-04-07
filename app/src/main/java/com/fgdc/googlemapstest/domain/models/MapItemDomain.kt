package com.fgdc.googlemapstest.domain.models

import com.fgdc.googlemapstest.ui.map.models.MapItemView

data class MapItemDomain(
    val id: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val companyZoneId: Int,
    val locationType: Int,
    val x: Double,
    val y: Double
) {
    fun toMapView() = MapItemView(name, lat, lon, companyZoneId)
}
