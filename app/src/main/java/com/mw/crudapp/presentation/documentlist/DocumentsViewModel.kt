package com.mw.crudapp.presentation.documentlist

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.models.DocumentHeaderDto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DocumentsViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao

    private val documents: MutableLiveData<List<DocumentHeaderDto>> = MutableLiveData()

    fun fetchDocuments(): MutableLiveData<List<DocumentHeaderDto>> {
        Observable.fromCallable { documentDao.getAllDocumentHeaders() }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { documents.postValue(it) }
                .addToDisposables()
        return documents
    }

    fun deleteDocument(documentId: Long): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { documentDao.deleteDocumentHeader(documentId) }
                .onErrorReturn { 0 }
                .flatMap { deleted ->
                    Observable.fromCallable { documentDao.deleteDocumentPositions(documentId) }
                            .onErrorReturn { 0 }
                            .map { deleted > 0 }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

}