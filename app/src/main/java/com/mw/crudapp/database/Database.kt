package com.mw.crudapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mw.crudapp.database.models.DocumentHeader

@Database(
    entities = [
        DocumentHeader::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

//    abstract fun userDao(): UserDao

}