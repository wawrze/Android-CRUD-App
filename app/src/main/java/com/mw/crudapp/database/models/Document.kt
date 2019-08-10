package com.mw.crudapp.database.models

import com.mw.crudapp.database.entities.DocumentHeader
import com.mw.crudapp.database.entities.DocumentPosition

data class Document(

    val header: DocumentHeader,

    val netValue: Double,

    val grossValue: Double,

    val positions: List<DocumentPosition>

)