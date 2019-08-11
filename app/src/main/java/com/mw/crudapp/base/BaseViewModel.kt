package com.mw.crudapp.base

import androidx.lifecycle.ViewModel
import com.mw.crudapp.injection.components.Injector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        @Suppress("LeakingThis")
        Injector.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun Disposable.addToDisposables() {
        compositeDisposable.add(this)
    }

}