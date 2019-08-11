package com.mw.crudapp.presentation.customers

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.CustomersDao
import com.mw.crudapp.database.entities.Customer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CustomersViewModel : BaseViewModel() {

    @Inject
    lateinit var customersDao: CustomersDao

    private val customers = MutableLiveData<List<Customer>>()

    fun fetchCustomers(): MutableLiveData<List<Customer>> {
        Observable.fromCallable { customersDao.getAllCustomers() }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { customers.postValue(it) }
                .addToDisposables()
        return customers
    }

    fun saveCustomer(customer: Customer): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { customersDao.insertCustomer(customer) }
                .onErrorReturn { 0L }
                .map { it > 0L }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

    fun deleteCustomer(customerId: Long): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        Observable.fromCallable { customersDao.deleteCustomer(customerId) }
                .onErrorReturn { 0 }
                .map { it > 0 }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result.postValue(it) }
                .addToDisposables()
        return result
    }

}