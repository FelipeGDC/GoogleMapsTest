package com.fgdc.googlemapstest.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.fgdc.googlemapstest.R
import com.fgdc.googlemapstest.databinding.ActivityMainBinding
import com.fgdc.googlemapstest.ui.base.BaseActivity
import com.fgdc.googlemapstest.utils.extensions.empty
import com.fgdc.googlemapstest.di.component.ViewComponent

class MainActivity : BaseActivity() {
    override fun initializeInjector(viewComponent: ViewComponent) = viewComponent.inject(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = when (destination.id) {
                R.id.mapView -> destination.label
                else -> String.empty()
            }

            binding.toolbar.visibility = when (destination.id) {
                else -> View.GONE
            }
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun showProgressStatus(viewStatus: Int) {
        binding.progress.visibility = viewStatus
    }
}
