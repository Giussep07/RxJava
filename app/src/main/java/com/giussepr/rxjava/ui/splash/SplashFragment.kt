package com.giussepr.rxjava.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.FragmentSplashBinding
import java.util.concurrent.TimeUnit

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash, container, false
        )

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }, TimeUnit.SECONDS.toMillis(1))

        return binding.root
    }
}