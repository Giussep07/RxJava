package com.giussepr.rxjava.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )

        binding.buttonIntroduction.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToIntroductionFragment())
        }

        binding.buttonDisposable.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDisposableFragment())
        }

        binding.buttonCompositeDisposable.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCompositeDisposableFragment())
        }

        binding.buttonCompositeDisposable.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCompositeDisposableFragment())
        }

        binding.buttonOperators.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOperatorsFragment())
        }

        binding.buttonTypesOfObservable.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTypesObservablesFragment())
        }

        binding.buttonSubject.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSubjectFragment())
        }

        binding.buttonBus.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBusFragment())
        }

        binding.buttonRxBinding.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRxBindingFragment())
        }

        binding.buttonBackPressure.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBackPressureFragment())
        }

        binding.buttonHotAndCold.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHotAndColdFragment())
        }

        return binding.root
    }
}