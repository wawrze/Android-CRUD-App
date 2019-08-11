package com.mw.crudapp.database.models

data class DocumentHeaderDto(
    val documentHeaderId: Long,
    val creationDate: Long,
    val customerId: Long,
    val customerName: String,
    val netValue: Double,
    val grossValue: Double = 0.0,
    val positionsCount: Int = 0
)