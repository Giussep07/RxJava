package com.giussepr.rxjava

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.giussepr.rxjava.databinding.ActivityMainBinding
import com.giussepr.rxjava.ui.bus.RxBus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val compositeDisposable = CompositeDisposable()

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

        compositeDisposable.add(RxBus.listen(String::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}