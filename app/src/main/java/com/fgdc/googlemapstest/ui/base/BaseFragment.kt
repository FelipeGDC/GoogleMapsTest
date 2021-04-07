package com.fgdc.googlemapstest.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fgdc.googlemapstest.GoogleMapsApplication
import com.fgdc.googlemapstest.di.module.ViewModule
import com.fgdc.googlemapstest.ui.MainActivity
import com.fgdc.googlemapstest.di.component.ViewComponent

abstract class BaseFragment : Fragment() {

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    private val fragmentComponent by lazy {
        (activity?.application as GoogleMapsApplication)
            .appComponent
            .viewComponent(ViewModule(activity as BaseActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(fragmentComponent)
    }

    protected fun showSpinner(show: Boolean?) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }

    private fun showProgress() = progressStatus(View.VISIBLE)

    private fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) {
                this.showProgressStatus(viewStatus)
            }
        }
}
