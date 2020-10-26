package com.giussepr.rxjava.ui.hotAndCold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.HotAndColdFragmentBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class HotAndColdFragment : Fragment() {

    private lateinit var binding: HotAndColdFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.hot_and_cold_fragment, container, false
        )

        //coldObservable()
        hotObservable()

        return binding.root
    }

    private fun coldObservable() {

        val cold: Observable<Long> = Observable.interval(500, TimeUnit.MILLISECONDS)

        cold.subscribe { number -> Timber.d("coldObservable -> number 1: $number") }

        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            Timber.e(e)
        }

        cold.subscribe { number -> Timber.d("coldObservable -> number 2: $number") }
    }

    private fun hotObservable() {

        val hot: ConnectableObservable<Long> = Observable.interval(500, TimeUnit.MILLISECONDS)
            .publish()

        hot.connect()

        Thread.sleep(1000)

        hot.subscribe { number -> Timber.d("hotObservable -> number 1: $number") }

        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            Timber.e(e)
        }

        hot.subscribe { number -> Timber.d("hotObservable -> number 2: $number") }
    }

}