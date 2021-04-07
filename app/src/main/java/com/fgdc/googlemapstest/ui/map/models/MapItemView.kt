package com.fgdc.googlemapstest.ui.map.models

import com.fgdc.googlemapstest.utils.extensions.empty
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MapItemView(
    val name: String,
    val lat: Double,
    val lon: Double,
    val companyZoneId: Int
) : ClusterItem {
    private val position: LatLng = LatLng(lat, lon)

    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSnippet(): String {
        return String.empty()
    }
}
