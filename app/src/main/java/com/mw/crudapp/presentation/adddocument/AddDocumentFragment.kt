package com.mw.crudapp.presentation.adddocument

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.database.models.Document
import com.mw.crudapp.database.models.DocumentHeaderDto
import com.mw.crudapp.database.models.DocumentPositionDto
import kotlinx.android.synthetic.main.fragment_add_document.*

class AddDocumentFragment : BaseFragment() {

    private lateinit var viewModel: AddDocumentViewModel
    private var adapter: AddDocumentPositionsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(AddDocumentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
        fragment_add_document_save.setOnClickListener {
            val customerId = fragment_add_document_customer_id_input.text.toString().toLongOrNull() ?: 0L
            val customerName = fragment_add_document_customer_name_input.text.toString()
            saveDocument(
                DocumentHeaderDto(customerId, customerName),
                adapter?.data ?: ArrayList()
            )
        }
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

    private fun saveDocument(documentHeader: DocumentHeaderDto, positions: List<DocumentPositionDto>) {
        activity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.window?.currentFocus?.applicationWindowToken, 0)
        }
        viewModel.saveNewDocument(Document(documentHeader, positions)).observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    Toast.makeText(context, resources.getString(R.string.document_saved), Toast.LENGTH_LONG).show()
                    navigate.popBackStack()
                } else {
                    Toast.makeText(context, resources.getString(R.string.document_save_error), Toast.LENGTH_LONG).show()
                }
            }
        )
    }

}