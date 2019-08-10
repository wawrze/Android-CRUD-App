package com.mw.crudapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.entities.DocumentHeader
import com.mw.crudapp.database.entities.DocumentPosition

@Database(
    entities = [
        DocumentHeader::class,
        DocumentPosition::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun documentDao(): DocumentDao

}