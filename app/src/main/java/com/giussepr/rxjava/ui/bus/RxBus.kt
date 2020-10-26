package com.giussepr.rxjava.ui.bus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

object RxBus {

    private val bus = PublishSubject.create<Any>()

    fun publish(event: Any) {
        bus.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = bus.ofType(eventType)
}