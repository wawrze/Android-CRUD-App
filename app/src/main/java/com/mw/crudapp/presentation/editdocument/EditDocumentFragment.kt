package com.mw.crudapp.presentation.editdocument

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment

class EditDocumentFragment : BaseFragment() {

    private lateinit var viewModel: EditDocumentViewModel
    private val args by navArgs<EditDocumentFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(EditDocumentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_edit_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        toolbarTitle = getString(R.string.edit_document_id, args.documentId.toString())
        navigationBack = true
    }

}