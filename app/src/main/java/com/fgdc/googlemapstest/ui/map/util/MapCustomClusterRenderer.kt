package com.fgdc.googlemapstest.ui.map.util

import android.content.Context
import com.fgdc.googlemapstest.ui.map.models.MapItemView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class MapCustomClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MapItemView>
) : DefaultClusterRenderer<MapItemView>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: MapItemView, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)
        val markerDescriptor: Float = when (item.companyZoneId) {
            378 -> {
                BitmapDescriptorFactory.HUE_YELLOW
            }
            382 -> {
                BitmapDescriptorFactory.HUE_ORANGE
            }
            402 -> {
                BitmapDescriptorFactory.HUE_GREEN
            }
            412 -> {
                BitmapDescriptorFactory.HUE_AZURE
            }
            473 -> {
                BitmapDescriptorFactory.HUE_RED
            }
            else -> {
                BitmapDescriptorFactory.HUE_VIOLET
            }
        }
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(markerDescriptor))
            .snippet(item.title)
    }
}
