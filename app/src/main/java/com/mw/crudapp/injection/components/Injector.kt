package com.mw.crudapp.injection.components

import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.presentation.MainActivity
import com.mw.crudapp.presentation.adddocument.AddDocumentViewModel
import com.mw.crudapp.presentation.documentlist.DocumentsViewModel
import com.mw.crudapp.presentation.documentpositions.DocumentPositionsViewModel
import com.mw.crudapp.presentation.editdocument.EditDocumentViewModel

object Injector {

    @Suppress("HasPlatformType")
    val injector = DaggerApplicationComponent.builder()

    fun inject(o: Any) {
        when (o) {
            is MainActivity -> injector.build().inject(o)
            is BaseFragment -> injector.build().inject(o)
            is DocumentsViewModel -> injector.build().inject(o)
            is DocumentPositionsViewModel -> injector.build().inject(o)
            is AddDocumentViewModel -> injector.build().inject(o)
            is EditDocumentViewModel -> injector.build().inject(o)
        }
    }

}