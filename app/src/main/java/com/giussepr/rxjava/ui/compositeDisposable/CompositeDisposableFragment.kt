package com.giussepr.rxjava.ui.compositeDisposable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.CompositeDisposableFragmentBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class CompositeDisposableFragment : Fragment() {

    private lateinit var binding: CompositeDisposableFragmentBinding

    private lateinit var numbersObserver: DisposableObserver<String>
    private lateinit var numbersLetterObserver: DisposableObserver<String>

    private lateinit var numbersObservable: Observable<String>
    private lateinit var numbersLetterObservable: Observable<String>

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.composite_disposable_fragment, container, false
        )

        numbersObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        numbersLetterObservable = Observable.just("one", "two", "three", "four", "five")

        numbersObserver = object : DisposableObserver<String>() {
            override fun onNext(number: String?) {
                Timber.d("onNextNumber, number $number")
            }

            override fun onError(e: Throwable?) {
                Timber.d("onErrorNumber")
            }

            override fun onComplete() {
                Timber.d("onCompleteNumber")
            }
        }

        numbersLetterObserver = object : DisposableObserver<String>() {
            override fun onNext(numberLetter: String?) {
                Timber.d("onNextNumberLetter, $numberLetter")
            }

            override fun onError(e: Throwable?) {
                Timber.d("onErrorNumberLetter")
            }

            override fun onComplete() {
                Timber.d("onCompleteNumberLetter")
            }
        }

        with(compositeDisposable) {
            add(
                numbersObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(numbersObserver)
            )
            add(
                numbersLetterObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(numbersLetterObserver)
            )
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}