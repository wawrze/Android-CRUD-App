package com.mw.crudapp.database.models

data class DocumentHeaderDto(
        val documentHeaderId: Long,
        val creationDate: Long,
        val customerId: Long,
        val customerName: String,
        val netValue: Double,
        val grossValue: Double = 0.0,
        val positionsCount: Int = 0
) {
    constructor(customerId: Long, customerName: String) : this(
            0,
            0,
            customerId,
            customerName,
            0.0,
            0.0,
            0
    )

    constructor(documentId: Long, creationDate: Long, customerId: Long, customerName: String) : this(
            documentId,
            creationDate,
            customerId,
            customerName,
            0.0,
            0.0,
            0
    )

}