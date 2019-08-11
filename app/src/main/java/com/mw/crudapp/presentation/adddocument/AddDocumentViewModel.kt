package com.mw.crudapp.presentation.adddocument

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.CustomersDao
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.dao.ProductsDao
import com.mw.crudapp.database.entities.Customer
import com.mw.crudapp.database.entities.Product
import com.mw.crudapp.database.models.Document
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddDocumentViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao
    @Inject
    lateinit var customersDao: CustomersDao
    @Inject
    lateinit var productsDao: ProductsDao

    private val customers = MutableLiveData<List<Customer>>()
    private val products = MutableLiveData<List<Product>>()

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

    fun fetchCustomers(): MutableLiveData<List<Customer>> {
        Observable.fromCallable { customersDao.getAllCustomers() }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { customers.postValue(it) }
                .addToDisposables()
        return customers
    }

    fun fetchProducts(): MutableLiveData<List<Product>> {
        Observable.fromCallable { productsDao.getAllProducts() }
            .onErrorReturn { ArrayList() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { products.postValue(it) }
            .addToDisposables()
        return products
    }

}