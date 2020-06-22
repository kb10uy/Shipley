package org.kb10uy.shipley.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * いい感じに Rx の unsubscribe をするやつ
 */
open class DisposableViewModel : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun registerDisposable(sub: Disposable) {
        disposables.add(sub)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
