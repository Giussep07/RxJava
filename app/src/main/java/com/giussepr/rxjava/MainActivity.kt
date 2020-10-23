package com.giussepr.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.giussepr.rxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.materialToolbar)

        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment
            )
        )

        NavigationUI.setupWithNavController(
            binding.materialToolbar,
            navController,
            appBarConfiguration
        )

        setupDestinationChangedListener()
    }

    private fun setupDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> changeToolbarVisibility(false)
                else -> changeToolbarVisibility(true)
            }
        }
    }

    private fun changeToolbarVisibility(isVisible: Boolean) {
        binding.materialToolbar.isVisible = isVisible
    }
}