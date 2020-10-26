package com.giussepr.rxjava.ui.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giussepr.rxjava.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber


class IntroductionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val observableNums: Observable<String> =
            Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val observerNums: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
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

        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }
}