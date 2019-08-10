package com.mw.crudapp.presentation.documentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.database.entities.DocumentHeader
import kotlinx.android.synthetic.main.fragment_documents.*

class DocumentsFragment : BaseFragment(), DocumentActions {

    private var adapter: DocumentsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.document_list
        navigationBack = false
    }

    private fun recyclerSetup() {
        if (adapter == null) {
            adapter = DocumentsAdapter(ArrayList(), this)
        }
        fragment_documents_recycler.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        fragment_documents_recycler.adapter = adapter
    }

    override fun addDocument() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDocument(documentId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editDocument(documentId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDocument(documentId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}