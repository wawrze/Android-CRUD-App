package com.mw.crudapp.presentation.documentlist

import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.DocumentDao
import javax.inject.Inject

class DocumentsViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao

}