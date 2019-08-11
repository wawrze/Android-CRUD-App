package com.mw.crudapp.database.models

data class DocumentPositionDto(
        val documentPositionId: Long,
        val documentHeaderId: Long,
        val productId: Long,
        val productName: String,
        val amount: Double,
        val netPrice: Double,
        val grossPrice: Double
) {
    val netValue: Double
        get() = amount * netPrice
    val grossValue: Double
        get() = amount * grossPrice
}