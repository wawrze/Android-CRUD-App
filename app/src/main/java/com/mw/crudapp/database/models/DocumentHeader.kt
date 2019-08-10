package com.mw.crudapp.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DocumentHeader(

    @PrimaryKey
    val documentHeaderId: Long

)