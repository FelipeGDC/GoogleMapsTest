package com.fgdc.googlemapstest.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fgdc.googlemapstest.GoogleMapsApplication
import com.fgdc.googlemapstest.di.module.ViewModule
import com.fgdc.googlemapstest.di.component.ViewComponent

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initializeInjector(viewComponent: ViewComponent)

    private val activityComponent by lazy {
        (application as GoogleMapsApplication)
            .appComponent
            .viewComponent(ViewModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(activityComponent)
    }
}
