package com.mw.crudapp.injection.components

import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.presentation.MainActivity
import com.mw.crudapp.presentation.documentlist.DocumentsViewModel

object Injector {

    @Suppress("HasPlatformType")
    val injector = DaggerApplicationComponent.builder()

    fun inject(o: Any) {
        when (o) {
            is MainActivity -> injector.build().inject(o)
            is BaseFragment -> injector.build().inject(o)
            is DocumentsViewModel -> injector.build().inject(o)
        }
    }

}