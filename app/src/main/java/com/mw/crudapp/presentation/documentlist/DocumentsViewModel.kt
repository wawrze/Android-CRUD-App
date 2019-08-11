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

    fun getDocuments(): MutableLiveData<List<DocumentHeaderDto>> {
        Observable.fromCallable { documentDao.getAllDocumentHeaders() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                documents.postValue(it)
            }
            .addToDisposables()
        return documents
    }

}