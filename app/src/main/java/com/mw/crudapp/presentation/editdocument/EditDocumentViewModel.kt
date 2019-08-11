package com.mw.crudapp.presentation.editdocument

import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.DocumentDao
import javax.inject.Inject

class EditDocumentViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao

}