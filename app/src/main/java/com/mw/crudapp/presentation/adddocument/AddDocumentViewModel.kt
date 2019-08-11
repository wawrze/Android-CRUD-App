package com.mw.crudapp.presentation.adddocument

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.models.Document
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddDocumentViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao

    fun saveNewDocument(document: Document): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { documentDao.insertDocumentWithPositions(document) }
            .onErrorReturn { false }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result.postValue(it) }
            .addToDisposables()
        return result
    }

}