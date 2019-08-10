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

    @Query(
        """
        SELECT
            dh.*,
            SUM(dp.amount * dp.netPrice) AS netValue,
            SUM(dp.amount * dp.grossPrice) AS grossValue,
            COUNT(dp.productName) AS positionsCount
        FROM DocumentHeader dh
            JOIN DocumentPosition dp ON dp.documentHeaderId = dh.documentHeaderId
        WHERE dh.documentHeaderId = :documentId
        GROUP BY dh.documentHeaderId
    """
    )
    abstract fun getDocumentHeaderById(documentId: Long): DocumentHeader

    @Query("SELECT * FROM DocumentPosition WHERE documentHeaderId = :documentId")
    abstract fun getDocumentPositionsByHeaderId(documentId: Long): List<DocumentPosition>

    @Query("SELECT * FROM DocumentHeader")
    abstract fun getAllDocumentHeaders(): List<DocumentHeader>

    fun getDocumentWithPositions(documentId: Long): Document {
        return Document(getDocumentHeaderById(documentId), getDocumentPositionsByHeaderId(documentId))
    }

}