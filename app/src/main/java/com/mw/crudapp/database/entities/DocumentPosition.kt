package com.mw.crudapp.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class DocumentPosition(

        @PrimaryKey(autoGenerate = true)
        var documentPositionId: Long = 0L,

        var documentHeaderId: Long = 0L,

        var productId: Long = 0L,

        var amount: Double = 0.0,

        var netPrice: Double = 0.0,

        var grossPrice: Double = 0.0

) {
    @Ignore
    constructor(
            documentHeaderId: Long,
            productId: Long,
            amount: Double,
            netPrice: Double,
            grossPrice: Double
    ) : this() {
        this.documentHeaderId = documentHeaderId
        this.productId = productId
        this.amount = amount
        this.netPrice = netPrice
        this.grossPrice = grossPrice
    }

}