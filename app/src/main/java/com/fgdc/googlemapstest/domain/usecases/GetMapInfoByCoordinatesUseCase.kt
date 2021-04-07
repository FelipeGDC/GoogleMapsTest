package com.fgdc.googlemapstest.domain.usecases

import com.fgdc.googlemapstest.data.repositories.MapRepository
import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.utils.extensions.orEmpty
import com.fgdc.googlemapstest.utils.functional.State
import com.google.android.gms.maps.model.LatLng

class GetMapInfoByCoordinatesUseCase(private val mapRepository: MapRepository) :
    BaseUseCase<State<List<MapItemDomain>>, GetMapInfoByCoordinatesUseCase.Params>() {

    override fun run(params: Params?) =
        mapRepository.getMapInfo(
            params?.city.orEmpty(),
            params?.lowerLeftLatLon.orEmpty(),
            params?.upperRightLatLon.orEmpty()
        )

    class Params(var city: String, var lowerLeftLatLon: LatLng, var upperRightLatLon: LatLng)
}
