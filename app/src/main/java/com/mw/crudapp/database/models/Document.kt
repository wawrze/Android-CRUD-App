package com.mw.crudapp.database.models

data class Document(
        val header: DocumentHeaderDto,
        val positions: List<DocumentPositionDto>
)