package com.fgdc.googlemapstest.utils.extensions

import com.google.android.gms.maps.model.LatLng

fun LatLng?.orEmpty(): LatLng = this ?: LatLng(0.0, 0.0)
