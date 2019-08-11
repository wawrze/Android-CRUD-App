package com.mw.crudapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mw.crudapp.database.entities.Customer

@Dao
interface CustomersDao {

    @Query("SELECT * FROM Customer ORDER BY customerId DESC")
    fun getAllCustomers(): List<Customer>

    @Query("DELETE FROM Customer WHERE customerId = :customerId")
    fun deleteCustomer(customerId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: Customer): Long

}