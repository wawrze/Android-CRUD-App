package com.mw.crudapp.presentation.documentlist

interface DocumentActions {

    fun addDocument()

    fun deleteDocument(documentId: Long)

    fun editDocument(documentId: Long)

    fun showDocument(documentId: Long)

}