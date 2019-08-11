package com.mw.crudapp.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Product(

        @PrimaryKey(autoGenerate = true)
        var productId: Long = 0L,

        var productName: String = ""

) {
    @Ignore
    constructor(productName: String) : this() {
        this.productName = productName
    }
}