package com.mw.crudapp.presentation.documentpositions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_document_positions.*

class DocumentPositionsDialog : BaseDialog() {

    companion object {

        private const val DOCUMENT_ID = "DOCUMENT_ID"

        fun newInstance(documentId: Long) = DocumentPositionsDialog().apply {
            arguments = Bundle().apply {
                putLong(DOCUMENT_ID, documentId)
            }
        }

    }

    private var adapter: DocumentPositionsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_document_positions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog_document_positions_close.setOnClickListener {
            dismiss()
        }
        dialog_document_positions_header.text = getString(R.string.document_with_id, arguments?.getLong(DOCUMENT_ID))
        recyclerSetup()
        observersSetup()
    }

    private fun recyclerSetup() {
        if (adapter == null) {
            adapter = DocumentPositionsAdapter(ArrayList())
        }
        dialog_document_positions_recycler.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        dialog_document_positions_recycler.adapter = adapter
    }

    private fun observersSetup() {
        // TODO observe view model fetch data
    }

}