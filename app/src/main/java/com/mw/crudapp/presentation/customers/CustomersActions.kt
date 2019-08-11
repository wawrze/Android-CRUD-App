package com.mw.crudapp.presentation.customers

import com.mw.crudapp.database.entities.Customer

interface CustomersActions {

    fun saveCustomer(customer: Customer)
    fun deleteCustomer(customerId: Long)

}