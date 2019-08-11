package com.mw.crudapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mw.crudapp.database.entities.Customer

@Dao
abstract class CustomersDao {

    @Query("SELECT * FROM Customer ORDER BY customerId DESC")
    abstract fun getAllCustomers(): List<Customer>

    fun deleteCustomer(customerId: Long): Int {
        return if (isCustomerInUse(customerId)) {
            0
        } else {
            proceedDeleteCustomer(customerId)
        }
    }

    @Query("SELECT EXISTS(SELECT customerId FROM DocumentHeader WHERE customerId = :customerId)")
    protected abstract fun isCustomerInUse(customerId: Long): Boolean

    @Query("DELETE FROM Customer WHERE customerId = :customerId")
    protected abstract fun proceedDeleteCustomer(customerId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCustomer(customer: Customer): Long

}