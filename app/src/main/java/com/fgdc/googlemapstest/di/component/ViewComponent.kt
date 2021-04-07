package com.fgdc.googlemapstest.di.component

import com.fgdc.googlemapstest.di.module.ViewModule
import com.fgdc.googlemapstest.di.scope.ViewScope
import com.fgdc.googlemapstest.ui.MainActivity
import com.fgdc.googlemapstest.ui.map.fragments.MapFragment
import dagger.Subcomponent

@ViewScope
@Subcomponent(
    modules = [
        ViewModule::class
    ]
)
interface ViewComponent {

    fun inject(activity: MainActivity)

    fun inject(mapFragment: MapFragment)
}
