package com.mw.crudapp.presentation.documentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment

class DocumentsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.document_list
        navigationBack = false
    }

}