package com.mw.crudapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mw.crudapp.database.dao.CustomersDao
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.dao.ProductsDao
import com.mw.crudapp.database.entities.Customer
import com.mw.crudapp.database.entities.DocumentHeader
import com.mw.crudapp.database.entities.DocumentPosition
import com.mw.crudapp.database.entities.Product

@Database(
        entities = [
            Customer::class,
            DocumentHeader::class,
            DocumentPosition::class,
            Product::class
        ],
        version = 1,
        exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun documentDao(): DocumentDao
    abstract fun customersDao(): CustomersDao
    abstract fun productDao(): ProductsDao

}