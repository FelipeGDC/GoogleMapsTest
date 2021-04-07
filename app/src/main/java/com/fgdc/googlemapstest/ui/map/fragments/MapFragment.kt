package com.fgdc.googlemapstest.ui.map.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fgdc.googlemapstest.BuildConfig
import com.fgdc.googlemapstest.R
import com.fgdc.googlemapstest.databinding.FragmentMapBinding
import com.fgdc.googlemapstest.di.component.ViewComponent
import com.fgdc.googlemapstest.ui.base.BaseFragment
import com.fgdc.googlemapstest.ui.map.models.MapItemView
import com.fgdc.googlemapstest.ui.map.util.MapCustomClusterRenderer
import com.fgdc.googlemapstest.ui.map.viewmodel.MapViewModel
import com.fgdc.googlemapstest.utils.exception.ErrorHandler
import com.fgdc.googlemapstest.utils.extensions.failure
import com.fgdc.googlemapstest.utils.extensions.observe
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import javax.inject.Inject

class MapFragment : BaseFragment(), GoogleMap.OnCameraMoveListener {
    @Inject
    lateinit var mapViewModel: MapViewModel

    override fun initializeInjector(viewComponent: ViewComponent) = viewComponent.inject(this)
    private lateinit var binding: FragmentMapBinding
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<MapItemView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(mapViewModel) {
            observe(showSpinner, ::showSpinner)
            failure(failure, ::handleFailure)
            observe(mapPositions, ::setMapInfo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMap()
    }

    private fun setUpMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            map = googleMap
            clusterManager = ClusterManager(context, map)
            clusterManager.renderer =
                MapCustomClusterRenderer(requireContext(), map, clusterManager)
            map.setOnCameraMoveListener(this)
            map.setOnCameraIdleListener(clusterManager)
            mapViewModel.getLocationInfo(
                BuildConfig.MAP_DEFAULT_CITY,
                LatLng(
                    BuildConfig.MAP_DEFAULT_LOWER_LEFT_LAT,
                    BuildConfig.MAP_DEFAULT_LOWER_LEFT_LON
                ),
                LatLng(
                    BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LAT,
                    BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LON
                )
            )
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LAT,
                        BuildConfig.MAP_DEFAULT_UPPER_RIGHT_LON
                    ),
                    15f
                )
            )
        }
    }

    private fun setMapInfo(mapView: List<MapItemView>?) {
        clusterManager.clearItems()
        mapView?.let {
            it.forEach { mapResponseItem ->
                val markerOptions = MarkerOptions()
                markerOptions.title(mapResponseItem.name)
                clusterManager.addItem(mapResponseItem)
            }
        }
        clusterManager.cluster()
    }

    private fun handleFailure(failure: Throwable?) {
        if (failure?.message == ErrorHandler.NETWORK_ERROR_MESSAGE) {
        }
    }

    override fun onCameraMove() {
        if (this::map.isInitialized) {
            val lowerLeftLatLon = map.projection.visibleRegion.latLngBounds.northeast
            val upperRightLatLon = map.projection.visibleRegion.latLngBounds.southwest

            if ((lowerLeftLatLon.latitude != 0.0 && lowerLeftLatLon.longitude != 0.0)) {
                mapViewModel.getLocationInfo(
                    BuildConfig.MAP_DEFAULT_CITY, lowerLeftLatLon, upperRightLatLon
                )
            }
        }
    }
}
