package com.giussepr.rxjava.ui.operators

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.OperatorsFragmentBinding
import com.giussepr.rxjava.model.User
import com.jakewharton.rxbinding2.widget.RxTextView
import hu.akarnokd.rxjava3.math.MathObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.*
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.observables.GroupedObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Timed
import timber.log.Timber
import java.util.concurrent.TimeUnit

class OperatorsFragment : Fragment() {

    companion object {
        const val EVEN_KEY = "Even"
        const val ODD_KEY = "Odd"
        const val LEFT_WINDOW_DURATION = 100L
        const val RIGHT_WINDOW_DURATION = 0L
    }

    private lateinit var binding: OperatorsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.operators_fragment, container, false
        )

        //testJust()
        //testJustArray()
        //testRange()
        //testRepeat()
        //testCreate()
        //testInterval()
        //testCreateException()
        //testCreateLongDuration()
        //testBuffer()
        //testMap()
        //testFlatMap()
        //testGroupBy()
        //testScan()
        //testWindow()
        //testDebounce()
        //testDistinct()
        //testElementAt()
        //testFilter()
        //testFirst()
        //testIgnoreElements()
        //testLast()
        //testSample()
        //testSkip()
        //testSkipLast()
        //testTake()
        //testTakeLast()
        //testCombineLast()
        //testJoin()
        //testMerge()
        //testZip()
        //testRetryWhen()
        //testDelay()
        //testDo()
        //testTimeInterval()
        //testTimeOut()
        //testTimeStamp()
        //testUsing()
        //testAll()
        //testAmb()
        //testContains()
        //testDefaultIfEmpty()
        //testSequenceEqual()
        //testSkipUntil()
        //testSkipWhile()
        //testTakeUntil()
        //testTakeWhile()
        //testAverage()
        //testCount()
        //testMax()
        testReduce()

        return binding.root
    }

    private fun testJust() {
        Timber.d("--------------------Just--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Just -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Just -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Just -> onError")
                }

                override fun onComplete() {
                    Timber.d("Just -> onComplete")
                }
            })
    }

    private fun testJustArray() {
        Timber.d("--------------------JustArray--------------------")
        val numbers = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        Observable.just(numbers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Array<String>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("JustArray -> onSubscribe")
                }

                override fun onNext(t: Array<String>?) {
                    Timber.d("JustArray -> onNext: ${t?.size}")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("JustArray -> onError")
                }

                override fun onComplete() {
                    Timber.d("JustArray -> onComplete")
                }
            })
    }

    private fun testRange() {
        Timber.d("--------------------Range--------------------")
        Observable.range(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Range -> onSubscribe")
                }

                override fun onNext(t: Int) {
                    Timber.d("Range -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Range -> onError")
                }

                override fun onComplete() {
                    Timber.d("Range -> onComplete")
                }
            })
    }

    private fun testRepeat() {
        Timber.d("--------------------Repeat--------------------")
        Observable.range(10, 5)
            .repeat(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Repeat -> onSubscribe")
                }

                override fun onNext(t: Int) {
                    Timber.d("Repeat -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Repeat -> onError")
                }

                override fun onComplete() {
                    Timber.d("Repeat -> onComplete")
                }
            })
    }

    private fun testCreate() {
        Timber.d("--------------------Create--------------------")
        Observable.create<String> {
            try {
                it.onNext("G")
                it.onNext("I")
                it.onNext("U")
                it.onNext("S")
                it.onNext("S")
                it.onNext("E")
                it.onNext("P")
                it.onComplete()
            } catch (e: Exception) {
                Timber.e("Create error, $e")
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Create -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("Create -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Create -> onError")
                }

                override fun onComplete() {
                    Timber.d("Create -> onComplete")
                }
            })
    }

    private fun testInterval() {
        Timber.d("--------------------Interval--------------------")
        Observable.interval(1, TimeUnit.SECONDS)
            .take(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Interval -> onSubscribe")
                }

                override fun onNext(t: Long) {
                    Timber.d("Interval -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Interval -> onError")
                }

                override fun onComplete() {
                    Timber.d("Interval -> onComplete")
                }
            })
    }

    private fun testCreateException() {
        Timber.d("--------------------CreateException--------------------")
        Observable.create<Int> {
            try {
                it.onNext(15 / 3)
                it.onNext(3 / 0)
            } catch (e: Exception) {
                Timber.e("CreateException error, $e")
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("CreateException -> onSubscribe")
                }

                override fun onNext(t: Int) {
                    Timber.d("CreateException -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("CreateException -> onError $e")
                }

                override fun onComplete() {
                    Timber.d("CreateException -> onComplete")
                }
            })
    }

    private fun longDurationTask(): String {
        try {
            Thread.sleep(10000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Finished"
    }

    private fun testCreateLongDuration() {
        Timber.d("--------------------CreateLongDuration--------------------")
        Observable.create<String> {
            try {
                it.onNext(longDurationTask())
            } catch (e: Exception) {
                Timber.e("CreateLongDuration error, $e")
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { string -> Timber.d("CreateLongDuration -> onNext: $string") },
                { e: Throwable -> Timber.d("CreateLongDuration -> onError $e") },
                { Timber.d("CreateLongDuration -> onComplete") }
            )
    }

    private fun testBuffer() {
        Timber.d("--------------------Buffer--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(3)
            .subscribe(object : Observer<List<Int>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Buffer -> onSubscribe")
                }

                override fun onNext(t: List<Int>) {
                    Timber.d("Buffer -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Buffer -> onError")
                }

                override fun onComplete() {
                    Timber.d("Buffer -> onComplete")
                }
            })
    }

    private fun testMap() {
        Timber.d("--------------------Map--------------------")
        Observable.fromArray(User.getUsers())
            .map { users ->
                val names: MutableList<String> = arrayListOf()

                users.forEach {
                    names.add(it.name)
                }

                names
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<String>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Map -> onSubscribe")
                }

                override fun onNext(t: List<String>) {
                    Timber.d("Map -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Map -> onError")
                }

                override fun onComplete() {
                    Timber.d("Map -> onComplete")
                }
            })
    }

    private fun testFlatMap() {
        Timber.d("--------------------FlatMap--------------------")
        Observable.just("item1", "item2", "item3")
            .flatMap { s ->
                Timber.d("FlatMap -> insideFlatMap, $s")
                Observable.just("$s 1", "$s 2", "$s 3")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("FlatMap -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("FlatMap -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("FlatMap -> onError")
                }

                override fun onComplete() {
                    Timber.d("FlatMap -> onComplete")
                }
            })
    }

    private fun testGroupBy() {
        Timber.d("--------------------GroupBy--------------------")

        val numbersObservable: Observable<Int> = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val observableGroup: Observable<GroupedObservable<String, Int>> = numbersObservable
            .groupBy { number ->
                if (number % 2 == 0) {
                    EVEN_KEY
                } else {
                    ODD_KEY
                }
            }

        observableGroup
            .subscribe(object : Observer<GroupedObservable<String, Int>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("GroupBy -> onSubscribe")
                }

                override fun onNext(groupedObservable: GroupedObservable<String, Int>) {
                    Timber.d("GroupBy -> onNext")
                    groupedObservable.subscribe(object : Observer<Int> {
                        override fun onSubscribe(d: Disposable?) {
                            Timber.d("GroupBy GroupedObservable -> onSubscribe")
                        }

                        override fun onNext(number: Int?) {
                            if (groupedObservable.key == EVEN_KEY) {
                                Timber.d("GroupBy GroupedObservable -> onNext its $EVEN_KEY $number")
                            } else {
                                Timber.d("GroupBy GroupedObservable -> onNext its $ODD_KEY $number")
                            }
                        }

                        override fun onError(e: Throwable?) {
                            Timber.d("GroupBy GroupedObservable -> onError")
                        }

                        override fun onComplete() {
                            Timber.d("GroupBy GroupedObservable -> onComplete")
                        }
                    })
                }

                override fun onError(e: Throwable?) {
                    Timber.d("GroupBy -> onError")
                }

                override fun onComplete() {
                    Timber.d("GroupBy -> onComplete")
                }
            })
    }

    private fun testScan() {
        Timber.d("--------------------Scan--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .scan { t1, t2 -> t1 + t2 }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Scan -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Scan -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Scan -> onError")
                }

                override fun onComplete() {
                    Timber.d("Scan -> onComplete")
                }
            })
    }

    private fun testWindow() {
        Timber.d("--------------------Window--------------------")
        val observable: Observable<Observable<Int>> = Observable
            .range(1, 150)
            .window(3)

        observable
            .subscribe(object : Observer<Observable<Int>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Window -> onSubscribe")
                }

                override fun onNext(t: Observable<Int>) {
                    Timber.d("Window -> onNext")
                    t.subscribe {
                        Timber.d("Item in window $it")
                    }
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Window -> onError")
                }

                override fun onComplete() {
                    Timber.d("Window -> onComplete")
                }
            })
    }

    private fun testDebounce() {
        Timber.d("--------------------Debounce--------------------")
        val observable: io.reactivex.disposables.Disposable? =
            RxTextView.textChanges(binding.editTextQuery)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { ttt -> ttt.toString() }
                .subscribe {
                    Timber.d("Debounce onNext: $it")
                    binding.textViewQuery.text = it
                }
    }

    private fun testDistinct() {
        Timber.d("--------------------Distinct--------------------")
        Observable.just(1, 2, 2, 3, 3, 3, 4, 5, 6, 6)
            .distinct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Distinct -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Distinct -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Distinct -> onError")
                }

                override fun onComplete() {
                    Timber.d("Distinct -> onComplete")
                }
            })
    }

    private fun testElementAt() {
        Timber.d("--------------------ElementAt--------------------")
        Observable.just(1, 2, 2, 3, 3, 3, 4, 5, 6, 6)
            .elementAt(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("ElementAt -> onSubscribe")
                }

                override fun onSuccess(t: Int?) {
                    Timber.d("ElementAt -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("ElementAt -> onError")
                }

                override fun onComplete() {
                    Timber.d("ElementAt -> onComplete")
                }
            })
    }

    private fun testFilter() {
        Timber.d("--------------------Filter--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter { t -> t % 2 == 0 }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Filter -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Filter -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Filter -> onError")
                }

                override fun onComplete() {
                    Timber.d("Filter -> onComplete")
                }
            })
    }

    private fun testFirst() {
        Timber.d("--------------------First--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .first(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("First -> onSubscribe")
                }

                override fun onSuccess(t: Int?) {
                    Timber.d("First -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("First -> onError")
                }
            })
    }

    private fun testIgnoreElements() {
        Timber.d("--------------------IgnoreElements--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .ignoreElements()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("IgnoreElements -> onSubscribe")
                }

                override fun onComplete() {
                    Timber.d("IgnoreElements -> onComplete")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("IgnoreElements -> onError")
                }
            })
    }

    private fun testLast() {
        Timber.d("--------------------Last--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .last(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Last -> onSubscribe")
                }

                override fun onSuccess(t: Int?) {
                    Timber.d("Last -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Last -> onError")
                }
            })
    }

    private fun testSample() {
        Timber.d("--------------------Sample--------------------")
        Observable.interval(500, TimeUnit.MILLISECONDS)
            .take(10)
            .sample(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Sample -> onSubscribe")
                }

                override fun onNext(t: Long?) {
                    Timber.d("Sample -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Sample -> onError")
                }

                override fun onComplete() {
                    Timber.d("Sample -> onComplete")
                }
            })
    }

    private fun testSkip() {
        Timber.d("--------------------Skip--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .skip(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Skip -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Skip -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Skip -> onError")
                }

                override fun onComplete() {
                    Timber.d("Skip -> onComplete")
                }
            })
    }

    private fun testSkipLast() {
        Timber.d("--------------------SkipLast--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .skipLast(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("SkipLast -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("SkipLast -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("SkipLast -> onError")
                }

                override fun onComplete() {
                    Timber.d("SkipLast -> onComplete")
                }
            })
    }

    private fun testTake() {
        Timber.d("--------------------Take--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .take(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Take -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Take -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Take -> onError")
                }

                override fun onComplete() {
                    Timber.d("Take -> onComplete")
                }
            })
    }

    private fun testTakeLast() {
        Timber.d("--------------------TakeLast--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .takeLast(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TakeLast -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("TakeLast -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TakeLast -> onError")
                }

                override fun onComplete() {
                    Timber.d("TakeLast -> onComplete")
                }
            })
    }

    private fun testCombineLast() {
        Timber.d("--------------------CombineLast--------------------")
        val observableOne: Observable<Long> =
            Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
        val observableTwo: Observable<Long> =
            Observable.interval(50, TimeUnit.MILLISECONDS).take(20)

        Observable.combineLatest(
            observableOne,
            observableTwo,
            object : BiFunction<Long, Long, String> {
                override fun apply(t1: Long?, t2: Long?): String {
                    return "$t1 and $t2"
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("CombineLast -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("CombineLast -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("CombineLast -> onError")
                }

                override fun onComplete() {
                    Timber.d("CombineLast -> onComplete")
                }
            })
    }

    private fun testJoin() {
        Timber.d("--------------------Join--------------------")

        val leftObservable: Observable<Long> =
            Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
        val rightObservable: Observable<Long> =
            Observable.interval(100, TimeUnit.MILLISECONDS).take(10)

        leftObservable.join(
            rightObservable,
            { Observable.timer(LEFT_WINDOW_DURATION, TimeUnit.MILLISECONDS) },
            { Observable.timer(RIGHT_WINDOW_DURATION, TimeUnit.MILLISECONDS) },
            { t1, t2 ->
                Timber.d("Join -> left $t1 and right $t2")
                t1 + t2
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Join -> onSubscribe")
                }

                override fun onNext(t: Long?) {
                    Timber.d("Join -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Join -> onError")
                }

                override fun onComplete() {
                    Timber.d("Join -> onComplete")
                }
            })
    }

    private fun testMerge() {
        Timber.d("--------------------Merge--------------------")
        val observableOne: Observable<String> = Observable.interval(2, TimeUnit.SECONDS)
            .take(10)
            .map { long -> "Group 1: $long" }

        val observableTwo: Observable<String> = Observable.interval(1, TimeUnit.SECONDS)
            .take(10)
            .map(object : Function<Long, String> {
                override fun apply(t: Long): String {
                    return "Group 2: $t"
                }
            })

        Observable.merge(observableOne, observableTwo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Merge -> onSubscribe")
                }

                override fun onNext(t: String?) {
                    Timber.d("Merge -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Merge -> onError")
                }

                override fun onComplete() {
                    Timber.d("Merge -> onComplete")
                }
            })
    }

    private fun testZip() {
        Timber.d("--------------------Zip--------------------")
        val observableOne: Observable<String> = Observable.interval(2, TimeUnit.SECONDS)
            .take(10)
            .map { long -> "Group 1: $long" }

        val observableTwo: Observable<String> = Observable.interval(1, TimeUnit.SECONDS)
            .take(10)
            .map { t -> "Group 2: $t" }

        Observable.zip(
            observableOne,
            observableTwo,
            { ob1, ob2 ->
                "$ob1 and $ob2"
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Zip -> onSubscribe")
                }

                override fun onNext(t: String?) {
                    Timber.d("Zip -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Zip -> onError")
                }

                override fun onComplete() {
                    Timber.d("Zip -> onComplete")
                }
            })
    }

    private fun testRetryWhen() {
        Timber.d("--------------------RetryWhen--------------------")
        Observable.create<String> {
            it.onNext("G")
            it.onError(Throwable("Test retry"))
        }
            .retryWhen { error -> error.retry() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Create -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("Create -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Create -> onError")
                }

                override fun onComplete() {
                    Timber.d("Create -> onComplete")
                }
            })
    }

    private fun testDelay() {
        Timber.d("--------------------Delay--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Delay -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Delay -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Delay -> onError")
                }

                override fun onComplete() {
                    Timber.d("Delay -> onComplete")
                }
            })
    }

    private fun testDo() {
        Timber.d("--------------------Do--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .doOnNext { number -> Timber.d("doOnNext -> $number") }
            .doAfterNext { number -> Timber.d("doAfterNext -> $number") }
            .doOnComplete { Timber.d("doOnComplete") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Do -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Do -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Do -> onError")
                }

                override fun onComplete() {
                    Timber.d("Do -> onComplete")
                }
            })
    }

    private fun testTimeInterval() {
        Timber.d("--------------------TimeInterval--------------------")
        Observable.interval(1, TimeUnit.SECONDS)
            .take(10)
            .timeInterval()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Timed<Long>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TimeInterval -> onSubscribe")
                }

                override fun onNext(t: Timed<Long>) {
                    Timber.d("TimeInterval -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TimeInterval -> onError")
                }

                override fun onComplete() {
                    Timber.d("TimeInterval -> onComplete")
                }
            })
    }

    private fun testTimeOut() {
        Timber.d("--------------------TimeOut--------------------")
        Observable.create<String> {
            try {
                it.onNext("G")
                it.onNext("I")
                it.onNext("U")
                Thread.sleep(1000)
                it.onNext("S")
                it.onNext("S")
                it.onNext("E")
                it.onNext("P")
                it.onComplete()
            } catch (e: Exception) {
                Timber.e("Create error, $e")
                it.onError(e)
            }
        }
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TimeOut -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("TimeOut -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TimeOut -> onError, $e")
                }

                override fun onComplete() {
                    Timber.d("TimeOut -> onComplete")
                }
            })
    }

    private fun testTimeStamp() {
        Timber.d("--------------------TimeStamp--------------------")
        Observable.create<String> {
            try {
                it.onNext("G")
                it.onNext("I")
                it.onNext("U")
                it.onNext("S")
                it.onNext("S")
                it.onNext("E")
                it.onNext("P")
                it.onComplete()
            } catch (e: Exception) {
                Timber.e("Create error, $e")
                it.onError(e)
            }
        }
            .timestamp()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Timed<String>> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TimeStamp -> onSubscribe")
                }

                override fun onNext(t: Timed<String>) {
                    Timber.d("TimeStamp -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TimeStamp -> onError, $e")
                }

                override fun onComplete() {
                    Timber.d("TimeStamp -> onComplete")
                }
            })
    }

    private fun testUsing() {
        Timber.d("--------------------Using--------------------")

        Observable.using(
            object : Supplier<String> {
                override fun get(): String {
                    return "Using"
                }
            },
            object : Function<String, ObservableSource<String>> {
                override fun apply(t: String): ObservableSource<String> {
                    return Observable.create { emitter ->
                        t.forEach {
                            emitter.onNext(it.toString())
                        }
                    }
                }
            },
            object : Consumer<String> {
                override fun accept(t: String?) {
                    Timber.d("Using -> Dispose $t")
                }
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Using -> onSubscribe")
                }

                override fun onNext(t: String) {
                    Timber.d("Using -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Using -> onError, $e")
                }

                override fun onComplete() {
                    Timber.d("Using -> onComplete")
                }
            })
    }

    private fun testAll() {
        Timber.d("--------------------All--------------------")
        Observable.just(1, -1, 3, -4, 5, -6, 7, -8, 9, -10)
            .all(object : Predicate<Int> {
                override fun test(number: Int): Boolean {
                    return number > 0
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("All -> onSubscribe")
                }

                override fun onSuccess(t: Boolean?) {
                    Timber.d("All -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("All -> onError")
                }
            })
    }

    private fun testAmb() {
        Timber.d("--------------------Amb--------------------")
        val observableOne = Observable.just(1, -2, 3, -4, 5, -6, 7, -8, 9, -10)
        val observableTwo = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        Observable.ambArray(
            observableOne.delay(1, TimeUnit.SECONDS),
            observableTwo
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Amb -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Amb -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Amb -> onError")
                }

                override fun onComplete() {
                    Timber.d("Amb -> onComplete")
                }
            })
    }

    private fun testContains() {
        Timber.d("--------------------Contains--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .contains(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Contains -> onSubscribe")
                }

                override fun onSuccess(t: Boolean?) {
                    Timber.d("Contains -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Contains -> onError")
                }
            })
    }

    private fun testDefaultIfEmpty() {
        Timber.d("--------------------DefaultIfEmpty--------------------")
        Observable.create<Int> {
            val num = 7
            if (num % 2 == 0) {
                it.onNext(num)
            }
            it.onComplete()
        }
            .defaultIfEmpty(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("DefaultIfEmpty -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("DefaultIfEmpty -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("DefaultIfEmpty -> onError")
                }

                override fun onComplete() {
                    Timber.d("DefaultIfEmtpty -> onComplete")
                }
            })
    }

    private fun testSequenceEqual() {
        Timber.d("--------------------SequenceEqual--------------------")
        val observableOne = Observable.just(1, -2, 3, -4, 5, -6, 7, -8, 9, -10)
        val observableTwo = Observable.just(1, -2, 3, -4, 5, -6, 7, -8, 9, -10)
        Observable.sequenceEqual(observableOne, observableTwo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("SequenceEqual -> onSubscribe")
                }

                override fun onSuccess(t: Boolean?) {
                    Timber.d("SequenceEqual -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("SequenceEqual -> onError")
                }
            })
    }

    private fun testSkipUntil() {
        Timber.d("--------------------SkipUntil--------------------")
        val observableOne = Observable.create<Int> {
            for (i in 1..10) {
                Thread.sleep(500)
                it.onNext(i)
            }
            it.onComplete()
        }

        val observableTwo = Observable.create<Int> {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3))
            it.onNext(4)
            it.onComplete()
        }

        observableOne.skipUntil(observableTwo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("SkipUntil -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("SkipUntil -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("SkipUntil -> onError")
                }

                override fun onComplete() {
                    Timber.d("SkipUntil -> onComplete")
                }
            })
    }

    private fun testSkipWhile() {
        Timber.d("--------------------SkipWhile--------------------")
        Observable.create<Int> {
            for (i in 1..10) {
                Thread.sleep(400)
                it.onNext(i)
            }
            it.onComplete()
        }
            .skipWhile { it <= 6 }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("SkipWhile -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("SkipWhile -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("SkipWhile -> onError")
                }

                override fun onComplete() {
                    Timber.d("SkipWhile -> onComplete")
                }
            })
    }

    private fun testTakeUntil() {
        Timber.d("--------------------TakeUntil--------------------")
        val observableOne = Observable.create<Int> {
            for (i in 1..10) {
                Thread.sleep(500)
                it.onNext(i)
            }
            it.onComplete()
        }

        val observableTwo: Observable<Int> = Observable.timer(3, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap Observable.create<Int> {
                    it.onNext(4)
                    it.onComplete()
                }
            }

        observableOne.takeUntil(observableTwo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TakeUntil -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("TakeUntil -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TakeUntil -> onError")
                }

                override fun onComplete() {
                    Timber.d("TakeUntil -> onComplete")
                }
            })
    }

    private fun testTakeWhile() {
        Timber.d("--------------------TakeWhile--------------------")
        Observable.create<Int> {
            for (i in 1..10) {
                Thread.sleep(400)
                it.onNext(i)
            }
            it.onComplete()
        }
            .takeWhile { it <= 4 }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("TakeWhile -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("TakeWhile -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("TakeWhile -> onError")
                }

                override fun onComplete() {
                    Timber.d("SkipWhile -> onComplete")
                }
            })
    }

    private fun testAverage() {
        Timber.d("--------------------Average--------------------")
        val observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        MathObservable.averageDouble(observable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Double> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Average -> onSubscribe")
                }

                override fun onNext(t: Double?) {
                    Timber.d("Average -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Average -> onError")
                }

                override fun onComplete() {
                    Timber.d("Average -> onComplete")
                }
            })
    }

    private fun testCount() {
        Timber.d("--------------------Count--------------------")
        val observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .count()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Long> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Count -> onSubscribe")
                }

                override fun onSuccess(t: Long?) {
                    Timber.d("Count -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Count -> onError")
                }
            })
    }

    /**
     * Applies the same for Min and Sum
     */
    private fun testMax() {
        Timber.d("--------------------Max--------------------")
        val observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        MathObservable.max(observable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Max -> onSubscribe")
                }

                override fun onNext(t: Int?) {
                    Timber.d("Max -> onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Max -> onError")
                }

                override fun onComplete() {
                    Timber.d("Max -> onComplete")
                }
            })
    }

    private fun testReduce() {
        Timber.d("--------------------Reduce--------------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .reduce { t1, t2 ->
                t1 * t2
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Timber.d("Reduce -> onSubscribe")
                }

                override fun onSuccess(t: Int?) {
                    Timber.d("Reduce -> onSuccess: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.d("Reduce -> onError")
                }

                override fun onComplete() {
                    Timber.d("Reduce -> onComplete")
                }
            })
    }

}