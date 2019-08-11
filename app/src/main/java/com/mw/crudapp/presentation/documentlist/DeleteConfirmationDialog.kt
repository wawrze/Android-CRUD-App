package com.mw.crudapp.presentation.documentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_confirmation.*

class DeleteConfirmationDialog : BaseDialog() {

    companion object {

        private const val DOCUMENT_ID = "DOCUMENT_ID"

        fun newInstance(documentId: Long, callBack: () -> Unit) = DeleteConfirmationDialog().apply {
            this.callBack = callBack
            arguments = Bundle().apply {
                putLong(DOCUMENT_ID, documentId)
            }
        }
    }

    private var callBack: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog_confirmation_message.text = getString(
            R.string.delete_confirmation_message,
            arguments?.getLong(DOCUMENT_ID)
        )
        dialog_confirmation_close.setOnClickListener { dismiss() }
        dialog_confirmation_confirm.setOnClickListener {
            callBack?.invoke()
            dismiss()
        }
        dialog_confirmation_cancel.setOnClickListener { dismiss() }
    }

}