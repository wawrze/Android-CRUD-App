package com.mw.crudapp.database.dao

import androidx.room.*
import com.mw.crudapp.database.entities.DocumentHeader
import com.mw.crudapp.database.entities.DocumentPosition
import com.mw.crudapp.database.models.Document

@Dao
abstract class DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHeaders(header: DocumentHeader): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHeaders(headers: List<DocumentHeader>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPositions(header: DocumentPosition): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPositions(headers: List<DocumentPosition>): List<Long>

    @Query("")
    @Transaction
    fun insertDocumentWithPositions(documentHeader: DocumentHeader, positions: List<DocumentPosition>): Boolean {
        val documentHeaderId = insertHeaders(documentHeader)
        if (documentHeaderId <= 0L) {
            return false
        }
        positions.forEach {
            it.documentHeaderId = documentHeaderId
        }
        val positionsIds = insertPositions(positions)
        return positionsIds.isNotEmpty()
    }

    @Query("SELECT * FROM DocumentHeader WHERE documentHeaderId = :documentId LIMIT 1")
    abstract fun getByIdDocumentHeader(documentId: Long): DocumentHeader

    @Query("SELECT * FROM DocumentPosition WHERE documentHeaderId = :documentId")
    abstract fun getDocumentPositionsByHeaderId(documentId: Long): List<DocumentPosition>

    @Query("SELECT * FROM DocumentHeader")
    abstract fun getAllDocumentHeaders(): List<DocumentHeader>

    fun getDocumentWithPositions(documentId: Long): Document {
        val positions = getDocumentPositionsByHeaderId(documentId)
        return Document(
            getByIdDocumentHeader(documentId),
            positions.map { it.amount * it.netPrice }.sum(),
            positions.map { it.amount * it.grossPrice }.sum(),
            positions
        )
    }

}