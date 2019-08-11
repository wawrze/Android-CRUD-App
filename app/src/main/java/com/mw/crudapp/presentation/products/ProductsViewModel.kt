package com.mw.crudapp.presentation.products

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.ProductsDao
import com.mw.crudapp.database.entities.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsViewModel : BaseViewModel() {

    @Inject
    lateinit var productsDao: ProductsDao

    private val products = MutableLiveData<List<Product>>()

    fun fetchProducts(): MutableLiveData<List<Product>> {
        Observable.fromCallable { productsDao.getAllProducts() }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { products.postValue(it) }
                .addToDisposables()
        return products
    }

    fun saveProduct(product: Product): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { productsDao.insertProduct(product) }
                .onErrorReturn { 0L }
                .map { it > 0L }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

    fun deleteCustomer(productId: Long): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { productsDao.deleteProduct(productId) }
                .onErrorReturn { 0 }
                .map { it > 0 }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

}