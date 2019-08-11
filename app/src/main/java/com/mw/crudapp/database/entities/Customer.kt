package com.mw.crudapp.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Customer(

        @PrimaryKey(autoGenerate = true)
        var customerId: Long = 0L,

        var customerName: String = ""

) {
    @Ignore
    constructor(customerName: String) : this() {
        this.customerName = customerName
    }
}