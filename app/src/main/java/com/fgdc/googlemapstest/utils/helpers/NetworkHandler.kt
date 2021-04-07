package com.fgdc.googlemapstest.utils.helpers

import android.content.Context
import com.fgdc.googlemapstest.utils.extensions.networkInfo
import javax.inject.Inject

interface NetworkHandler {
    val isConnected: Boolean
}

class NetworkHandlerImpl
@Inject constructor(private val context: Context) : NetworkHandler {
    override val isConnected get() = context.networkInfo
}
