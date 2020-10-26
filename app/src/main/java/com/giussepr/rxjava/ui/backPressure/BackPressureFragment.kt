package com.giussepr.rxjava.ui.backPressure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.BackPressureFragmentBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.reactivestreams.Subscription
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BackPressureFragment : Fragment() {

    private lateinit var binding: BackPressureFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.back_pressure_fragment, container, false
        )

        //generateManualBackPressure()
        //backPressureBuffer()
        backPressureDrop()

        return binding.root
    }

    private fun generateManualBackPressure() {
        val source: PublishSubject<Int> = PublishSubject.create()

        source
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("generateManualBackPressure -> onSubscribe")
                }

                override fun onNext(t: Int) {
                    longTask(t)
                }

                override fun onError(e: Throwable?) {
                    Timber.d("generateManualBackPressure -> onError")
                }

                override fun onComplete() {
                    Timber.d("generateManualBackPressure -> onComplete")
                }
            })

        for (i in 1..10) {
            source.onNext(i)
            Timber.d("creating observable item: $i")
        }
        source.onComplete()
    }

    private fun longTask(number: Int) {
        Thread.sleep(1000)
        Timber.d("Consuming observable: $number")
    }

    private fun backPressureBuffer() {
        val source: Flowable<Long> = Flowable.interval(1, TimeUnit.MILLISECONDS)

        source
            .onBackpressureBuffer(1000)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { number ->
                    Timber.d("onNext: $number")
                    Thread.sleep(100)
                },
                { e -> Timber.e(e) }
            )

    }

    private fun backPressureDrop() {
        val source: Flowable<Long> = Flowable.interval(1, TimeUnit.MILLISECONDS)

        source
            .onBackpressureDrop()
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { number ->
                    Timber.d("onNext: $number")
                    Thread.sleep(100)
                },
                { e -> Timber.e(e) }
            )

    }
}