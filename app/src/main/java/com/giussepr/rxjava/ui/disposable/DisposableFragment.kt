package com.giussepr.rxjava.ui.disposable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.DisposableFragmentBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class DisposableFragment : Fragment() {

    private lateinit var binding: DisposableFragmentBinding
    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.disposable_fragment, container, false
        )

        val observableNums: Observable<String> =
            Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val observerNums: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Timber.d("onSubscribe, Thread: ${Thread.currentThread().name}")
            }

            override fun onNext(number: String?) {
                Timber.d("onNext, Number : $number, Thread: ${Thread.currentThread().name}")
            }

            override fun onError(e: Throwable?) {
                Timber.d("onError, Thread: ${Thread.currentThread().name}")
            }

            override fun onComplete() {
                Timber.d("onComplete, Thread: ${Thread.currentThread().name}")
            }
        }

        observableNums
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerNums)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy, isDispose: ${disposable?.isDisposed} Thread: ${Thread.currentThread().name}")
        disposable?.dispose()
        Timber.d("onDestroy, isDispose: ${disposable?.isDisposed} Thread: ${Thread.currentThread().name}")
    }
}