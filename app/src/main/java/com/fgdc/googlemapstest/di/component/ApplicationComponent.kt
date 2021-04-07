package com.fgdc.googlemapstest.di.component

import com.fgdc.googlemapstest.GoogleMapsApplication
import com.fgdc.googlemapstest.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        DataModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: GoogleMapsApplication)

    fun viewComponent(viewModule: ViewModule): ViewComponent
}
