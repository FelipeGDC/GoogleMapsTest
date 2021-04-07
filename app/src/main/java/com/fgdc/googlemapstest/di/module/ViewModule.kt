package com.fgdc.googlemapstest.di.module

import android.app.Activity
import com.fgdc.googlemapstest.di.scope.ViewScope
import com.fgdc.googlemapstest.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val activity: BaseActivity) {

    @Provides
    @ViewScope
    internal fun activity(): Activity {
        return activity
    }
}
