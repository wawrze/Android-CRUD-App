package com.mw.crudapp.presentation.adddocument

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_document.*

class AddDocumentFragment : BaseFragment() {

    private var adapter: AddDocumentPositionsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.new_document
        navigationBack = true
    }

    private fun recyclerSetup() {
        if (adapter == null) {
            adapter = AddDocumentPositionsAdapter()
        }
        fragment_add_document_recycler.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        fragment_add_document_recycler.adapter = adapter
    }

}