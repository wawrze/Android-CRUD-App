package com.mw.crudapp.presentation.documentpositions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseDialog

class DocumentPositionsDialog : BaseDialog() {

    companion object {

        private const val DOCUMENT_ID = "DOCUMENT_ID"

        fun newInstance(documentId: Long) = DocumentPositionsDialog().apply {
            arguments = Bundle().apply {
                putLong(DOCUMENT_ID, documentId)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_document_positions, container, false)
    }

}