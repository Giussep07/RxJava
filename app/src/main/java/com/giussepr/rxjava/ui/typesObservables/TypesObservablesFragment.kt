package com.giussepr.rxjava.ui.typesObservables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.TypesObservablesFragmentBinding
import com.giussepr.rxjava.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class TypesObservablesFragment : Fragment() {

    private lateinit var binding: TypesObservablesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.types_observables_fragment, container, false
        )

        //testObservable()
        //testSingle()
        //testMaybe()
        //testCompletable()
        testFlowable()

        return binding.root
    }

    private fun testObservable() {
        Timber.d("--------------------Observable--------------------")
        val users = User.getUsers()

        val observableUser: Observable<User> = Observable.create { emitter ->
            users.forEach {
                emitter.onNext(it)
            }
            emitter.onComplete()
        }

        val observer: Observer<User> = object : Observer<User> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("Observable -> onSubscribe")
            }

            override fun onNext(t: User) {
                Timber.d("Observable -> onNext: ${t.name}")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Observable -> onError")
            }

            override fun onComplete() {
                Timber.d("Observable -> onComplete")
            }
        }

        observableUser
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun testSingle() {
        Timber.d("--------------------Single--------------------")

        val singleUser: Single<User> = Single.create { emitter ->
            emitter.onSuccess(User(1, "Giussep Ricardo", 26, "Blue"))
        }

        val observer: SingleObserver<User> = object : SingleObserver<User> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("Single -> onSubscribe")
            }

            override fun onSuccess(t: User) {
                Timber.d("Single -> onSuccess: ${t.name}")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Single -> onError")
            }
        }

        singleUser
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun testMaybe() {
        Timber.d("--------------------Maybe--------------------")

        val maybeUser: Maybe<User> = Maybe.create { emitter ->
            emitter.onSuccess(User(1, "Giussep Ricardo", 26, "Blue"))
            emitter.onComplete()
        }

        val maybeEmpty = Maybe.empty<User>()

        val observer: MaybeObserver<User> = object : MaybeObserver<User> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("Maybe -> onSubscribe")
            }

            override fun onSuccess(t: User) {
                Timber.d("Maybe -> onSuccess: ${t.name}")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Maybe -> onError")
            }

            override fun onComplete() {
                Timber.d("Maybe -> onComplete")
            }
        }

        maybeUser
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun testCompletable() {
        Timber.d("--------------------Completable--------------------")

        val completableUser: Completable = Completable.create { emitter ->
            emitter.onComplete()
        }

        val observer: CompletableObserver = object : CompletableObserver {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("Completable -> onSubscribe")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Completable -> onError")
            }

            override fun onComplete() {
                Timber.d("Completable -> onComplete")
            }
        }

        completableUser
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private fun testFlowable() {
        Timber.d("--------------------Flowable--------------------")

        val completableUser: Flowable<Int> = Flowable.range(1, 10000)

        val observer: MaybeObserver<Int> = object : MaybeObserver<Int> {
            override fun onSubscribe(d: Disposable?) {
                Timber.d("Flowable -> onSubscribe")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Flowable -> onError")
            }

            override fun onComplete() {
                Timber.d("Flowable -> onComplete")
            }

            override fun onSuccess(t: Int?) {
                Timber.d("Flowable -> onSuccess, $t")
            }
        }

        completableUser
            .reduce { t1, t2 -> t1 + t2 }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

}