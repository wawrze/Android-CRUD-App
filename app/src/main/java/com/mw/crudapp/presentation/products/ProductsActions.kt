package com.mw.crudapp.presentation.products

import com.mw.crudapp.database.entities.Product

interface ProductsActions {

    fun saveProduct(product: Product)
    fun deleteProduct(productId: Long)

}