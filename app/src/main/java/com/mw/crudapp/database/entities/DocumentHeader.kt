package com.mw.crudapp.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class DocumentHeader(

    @PrimaryKey(autoGenerate = true)
    var documentHeaderId: Long = 0L,

    var creationDate: Long = System.currentTimeMillis(),

    var customerId: Long = 0L,

    var customerName: String = "",

    @Ignore
    var netValue: Double = 0.0,

    @Ignore
    var grossValue: Double = 0.0

) {
    @Ignore
    constructor(customerId: Long, customerName: String) : this() {
        this.customerId = customerId
        this.customerName = customerName
    }
}