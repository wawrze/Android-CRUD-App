package com.mw.crudapp.database.models

import com.mw.crudapp.database.entities.DocumentPosition

data class Document(

    val header: DocumentHeaderDto,

    val positions: List<DocumentPosition>

)