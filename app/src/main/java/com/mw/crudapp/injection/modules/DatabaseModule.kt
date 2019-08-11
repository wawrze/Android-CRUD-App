package com.mw.crudapp.injection.modules

import android.content.Context
import androidx.room.Room
import com.mw.crudapp.database.Database
import com.mw.crudapp.database.dao.CustomersDao
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.dao.ProductsDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val context: Context) {

    companion object {
        private const val DB_NAME = "crud_app.db"
    }

    private lateinit var dataBase: Database

    @Provides
    fun provideDataBase(): Database {
        if (!::dataBase.isInitialized) {
            dataBase = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .build()
        }
        return dataBase
    }

    @Provides
    fun provideDocumentDao(database: Database): DocumentDao = database.documentDao()

    @Provides
    fun provideCustomersDao(database: Database): CustomersDao = database.customersDao()

    @Provides
    fun provideProductsDao(database: Database): ProductsDao = database.productDao()

}