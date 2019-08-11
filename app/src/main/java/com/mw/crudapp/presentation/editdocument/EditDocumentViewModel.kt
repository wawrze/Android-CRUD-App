package com.mw.crudapp.presentation.editdocument

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.CustomersDao
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.entities.Customer
import com.mw.crudapp.database.models.Document
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditDocumentViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao
    @Inject
    lateinit var customersDao: CustomersDao

    private val documentData = MutableLiveData<Document?>()
    private val customers = MutableLiveData<List<Customer>>()

    fun fetchDocumentData(documentId: Long): MutableLiveData<Document?> {
        Observable.fromCallable { documentDao.getDocumentWithPositions(documentId) }
                .onErrorReturn { null }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { documentData.postValue(it) }
                .addToDisposables()
        return documentData
    }

    fun saveDocument(document: Document): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { documentDao.insertDocumentWithPositions(document) }
                .onErrorReturn { false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

    fun fetchCustomers(): MutableLiveData<List<Customer>> {
        Observable.fromCallable { customersDao.getAllCustomers() }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { customers.postValue(it) }
                .addToDisposables()
        return customers
    }

}