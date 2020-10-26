package com.giussepr.rxjava.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.SubjectFragmentBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import timber.log.Timber

class SubjectFragment : Fragment() {

    private lateinit var binding: SubjectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.subject_fragment, container, false
        )

        //testPublishSubject()
        //testReplaySubject()
        //testAsyncSubject()
        testSubjectLikeObserverAndObservable()

        return binding.root
    }

    private fun testPublishSubject() {
        Timber.d("--------------------PublishSubject--------------------")
        val publishSubject: PublishSubject<String> = PublishSubject.create()

        val observerOne = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerOne -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerOne -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerOne -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerOne -> onComplete")
            }
        }

        val observerTwo = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerTwo -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerTwo -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerTwo -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerTwo -> onComplete")
            }
        }

        with(publishSubject) {
            subscribe(observerOne)
            onNext("G")
            onNext("I")
            onNext("U")
            subscribe(observerTwo)
            onNext("S")
            onNext("S")
            onNext("E")
            onNext("P")
            onComplete()
        }
    }

    private fun testReplaySubject() {
        Timber.d("--------------------ReplaySubject--------------------")
        val replaySubject: ReplaySubject<String> = ReplaySubject.create()

        val observerOne = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerOne -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerOne -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerOne -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerOne -> onComplete")
            }
        }

        val observerTwo = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerTwo -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerTwo -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerTwo -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerTwo -> onComplete")
            }
        }

        with(replaySubject) {
            subscribe(observerOne)
            onNext("G")
            onNext("I")
            onNext("U")
            subscribe(observerTwo)
            onNext("S")
            onNext("S")
            onNext("E")
            onNext("P")
            onComplete()
        }
    }

    private fun testAsyncSubject() {
        Timber.d("--------------------AsyncSubject--------------------")
        val asyncSubject: AsyncSubject<String> = AsyncSubject.create()

        val observerOne = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerOne -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerOne -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerOne -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerOne -> onComplete")
            }
        }

        val observerTwo = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerTwo -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerTwo -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerTwo -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerTwo -> onComplete")
            }
        }

        with(asyncSubject) {
            subscribe(observerOne)
            onNext("G")
            onNext("I")
            onNext("U")
            onNext("S")
            onNext("S")
            onNext("E")
            onNext("P")
            onComplete()
            subscribe(observerTwo)
        }
    }

    private fun testSubjectLikeObserverAndObservable() {
        Timber.d("--------------------SubjectLikeObserverAndObservable--------------------")
        val observable: Observable<String> = Observable.just("Giussep", "Lizeth")

        val replaySubject: ReplaySubject<String> = ReplaySubject.create()

        observable.subscribe(replaySubject)

        val observerOne = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerOne -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerOne -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerOne -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerOne -> onComplete")
            }
        }

        val observerTwo = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("PublishSubject -> observerTwo -> onSubscribe")
            }

            override fun onNext(t: String?) {
                Timber.d("PublishSubject -> observerTwo -> onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Timber.d("PublishSubject -> observerTwo -> onError")
            }

            override fun onComplete() {
                Timber.d("PublishSubject -> observerTwo -> onComplete")
            }
        }

        replaySubject.subscribe(observerOne)
        replaySubject.subscribe(observerTwo)
    }

}