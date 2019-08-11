package com.mw.crudapp.presentation.editdocument

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.database.models.Document
import com.mw.crudapp.database.models.DocumentHeaderDto
import com.mw.crudapp.database.models.DocumentPositionDto
import kotlinx.android.synthetic.main.fragment_add_document.*

class EditDocumentFragment : BaseFragment() {

    private lateinit var viewModel: EditDocumentViewModel
    private val args by navArgs<EditDocumentFragmentArgs>()
    private lateinit var documentHeader: DocumentHeaderDto

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(EditDocumentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        toolbarTitle = getString(R.string.edit_document_id, args.documentId.toString())
        navigationBack = true
    }

    private fun setupView() {
        viewModel.fetchDocumentData(args.documentId).observe(
            viewLifecycleOwner,
            Observer {
                it?.let { document ->
                    documentHeader = document.header
                    fragment_add_document_customer_id_input.setText(document.header.customerId.toString())
                    fragment_add_document_customer_name_input.setText(document.header.customerName)
                    // TODO set adapter data
                }
            }
        )
        fragment_add_document_save.setOnClickListener {
            val customerId = fragment_add_document_customer_id_input.text.toString().toLongOrNull()
                ?: 0L
            val customerName = fragment_add_document_customer_name_input.text.toString()
            saveDocument(
                DocumentHeaderDto(
                    documentHeader.documentHeaderId,
                    documentHeader.creationDate,
                    customerId,
                    customerName
                ),
                ArrayList() // TODO pass adapters data
            )
        }
    }

    private fun saveDocument(documentHeader: DocumentHeaderDto, positions: List<DocumentPositionDto>) {
        activity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.window?.currentFocus?.applicationWindowToken, 0)
        }
        viewModel.saveDocument(Document(documentHeader, positions)).observe(
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