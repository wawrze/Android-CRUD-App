package com.mw.crudapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mw.crudapp.database.entities.Product

@Dao
abstract class ProductsDao {

    @Query("SELECT * FROM Product ORDER BY productId DESC")
    abstract fun getAllProducts(): List<Product>

    fun deleteProduct(productId: Long): Int {
        return if (isProductInUse(productId)) {
            0
        } else {
            proceedDeleteProduct(productId)
        }
    }

    @Query("SELECT EXISTS(SELECT productId FROM DocumentPosition WHERE productId = :productId)")
    protected abstract fun isProductInUse(productId: Long): Boolean

    @Query("DELETE FROM Product WHERE productId = :productId")
    protected abstract fun proceedDeleteProduct(productId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProduct(product: Product): Long

}