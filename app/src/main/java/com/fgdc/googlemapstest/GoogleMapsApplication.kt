package com.fgdc.googlemapstest

import android.app.Application
import com.fgdc.googlemapstest.di.module.ApplicationModule
import com.fgdc.googlemapstest.di.component.ApplicationComponent
import com.fgdc.googlemapstest.di.component.DaggerApplicationComponent

class GoogleMapsApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
