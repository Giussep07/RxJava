package com.giussepr.rxjava.ui.rxBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.giussepr.rxjava.R
import com.giussepr.rxjava.databinding.RxBindingFragmentBinding
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RxBindingFragment : Fragment() {

    private lateinit var binding: RxBindingFragmentBinding

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.rx_binding_fragment, container, false
        )

        val disposableButton1 = RxView.clicks(binding.buttonRxBinding1)
            .debounce(1, TimeUnit.SECONDS)
            .subscribe(object : Consumer<Any> {
                override fun accept(t: Any) {
                    Timber.d("onClick using Rx")
                }
            })

        binding.buttonRxBinding2.setOnClickListener {
            Timber.d("normal onClick")
        }

        val disposableEditText1 = RxTextView.textChanges(binding.editTextRxBinding1)
            .debounce(1, TimeUnit.SECONDS)
            .subscribe {
                Timber.d("onTextChange: $it")
            }

        with(disposable) {
            add(disposableButton1)
            add(disposableEditText1)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}