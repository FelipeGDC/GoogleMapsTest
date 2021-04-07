package com.fgdc.googlemapstest.data.datasource.remote.responses

import com.fgdc.googlemapstest.domain.models.MapItemDomain
import com.fgdc.googlemapstest.utils.extensions.orEmpty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MapResponseItem(
    @Json(name = "allowDropoff")
    var allowDropoff: Boolean?,
    @Json(name = "availableResources")
    var availableResources: Int?,
    @Json(name = "batteryLevel")
    var batteryLevel: Int?,
    @Json(name = "bikesAvailable")
    var bikesAvailable: Int?,
    @Json(name = "companyZoneId")
    var companyZoneId: Int?,
    @Json(name = "helmets")
    var helmets: Int?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "lat")
    var lat: Double?,
    @Json(name = "licencePlate")
    var licencePlate: String?,
    @Json(name = "locationType")
    var locationType: Int?,
    @Json(name = "lon")
    var lon: Double?,
    @Json(name = "model")
    var model: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "range")
    var range: Int?,
    @Json(name = "realTimeData")
    var realTimeData: Boolean?,
    @Json(name = "resourceImageId")
    var resourceImageId: String?,
    @Json(name = "resourceType")
    var resourceType: String?,
    @Json(name = "scheduledArrival")
    var scheduledArrival: Int?,
    @Json(name = "spacesAvailable")
    var spacesAvailable: Int?,
    @Json(name = "station")
    var station: Boolean?,
    @Json(name = "x")
    var x: Double?,
    @Json(name = "y")
    var y: Double?
) {
    fun toMapDomain() = MapItemDomain(
        id.orEmpty(),
        name.orEmpty(),
        y.orEmpty(),
        x.orEmpty(),
        companyZoneId.orEmpty(),
        locationType.orEmpty(),
        x.orEmpty(),
        y.orEmpty()
    )
}
