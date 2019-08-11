package com.mw.crudapp.presentation.documentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_documents.*

class DocumentsFragment : BaseFragment(), DocumentActions {

    private lateinit var viewModel: DocumentsViewModel
    private var adapter: DocumentsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(DocumentsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_documents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
        observersSetup()
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

    private fun observersSetup() {
        viewModel.getDocuments().observe(
            viewLifecycleOwner,
            Observer {
                adapter?.updateData(it)
            }
        )
    }

    override fun addDocument() {

    }

    override fun deleteDocument(documentId: Long) {

    }

    override fun editDocument(documentId: Long) {

    }

    override fun showDocument(documentId: Long) {

    }

}