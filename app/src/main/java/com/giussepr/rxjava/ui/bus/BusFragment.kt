package com.giussepr.rxjava.ui.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.BusFragmentBinding

class BusFragment : Fragment() {

    private lateinit var binding: BusFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.bus_fragment, container, false
        )

        binding.buttonSendMessage.setOnClickListener {
            RxBus.publish("Message from BusFragment")
        }

        return binding.root
    }

}