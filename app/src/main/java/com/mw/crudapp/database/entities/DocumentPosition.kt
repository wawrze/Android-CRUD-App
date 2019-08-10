package com.mw.crudapp.database.entities

import androidx.room.Entity
import androidx.room.Ignore

@Entity(primaryKeys = ["documentHeaderId", "productName"])
data class DocumentPosition(

    var documentHeaderId: Long = 0L,

    var productName: String = "",

    var amount: Double = 0.0,

    var netPrice: Double = 0.0,

    var grossPrice: Double = 0.0

) {
    @Ignore
    constructor(productName: String, amount: Double, netPrice: Double, grossPrice: Double) : this() {
        this.productName = productName
        this.amount = amount
        this.netPrice = netPrice
        this.grossPrice = grossPrice
    }
}