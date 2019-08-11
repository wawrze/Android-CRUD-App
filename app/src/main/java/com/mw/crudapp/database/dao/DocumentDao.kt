package com.mw.crudapp.database.dao

import androidx.room.*
import com.mw.crudapp.database.entities.DocumentHeader
import com.mw.crudapp.database.entities.DocumentPosition
import com.mw.crudapp.database.models.Document
import com.mw.crudapp.database.models.DocumentHeaderDto
import com.mw.crudapp.database.models.DocumentPositionDto

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
    fun insertDocumentWithPositions(document: Document): Boolean {
        val documentHeader = if (document.header.documentHeaderId > 0) {
            DocumentHeader(
                    document.header.documentHeaderId,
                    document.header.creationDate,
                    document.header.customerId
            )
        } else {
            DocumentHeader(document.header.customerId, document.header.customerName)
        }
        val documentHeaderId = insertHeaders(documentHeader)
        if (documentHeaderId <= 0L) {
            return false
        }
        deleteDocumentPositions(documentHeaderId)
        val positionsIds = insertPositions(
                document.positions.map {
                    DocumentPosition(
                            documentHeaderId,
                            it.productId,
                            it.amount,
                            it.netPrice,
                            it.grossPrice
                    )
                }
        )
        return positionsIds.isNotEmpty()
    }

    @Query(
            """
        SELECT
            dh.*,
            c.customerName AS customerName,
            SUM(dp.amount * dp.netPrice) AS netValue,
            SUM(dp.amount * dp.grossPrice) AS grossValue,
            COUNT(dp.productId) AS positionsCount
        FROM DocumentHeader dh
            JOIN DocumentPosition dp ON dp.documentHeaderId = dh.documentHeaderId
            JOIN Customer c ON c.customerId = dh.customerId
        WHERE dh.documentHeaderId = :documentId
        GROUP BY dh.documentHeaderId
    """
    )
    abstract fun getDocumentHeaderById(documentId: Long): DocumentHeaderDto

    @Query(
            """
            SELECT
                dp.*,
                p.productName
            FROM DocumentPosition dp
                JOIN Product p ON p.productId = dp.productId 
            WHERE documentHeaderId = :documentId
    """
    )
    abstract fun getDocumentPositionsByHeaderId(documentId: Long): List<DocumentPositionDto>

    @Query(
            """
        SELECT
            dh.*,
            c.customerName AS customerName,
            SUM(dp.amount * dp.netPrice) AS netValue,
            SUM(dp.amount * dp.grossPrice) AS grossValue,
            COUNT(dp.productId) AS positionsCount
        FROM DocumentHeader dh
            JOIN DocumentPosition dp ON dp.documentHeaderId = dh.documentHeaderId
            JOIN Customer c ON c.customerId = dh.customerId
        GROUP BY dh.documentHeaderId
    """
    )
    abstract fun getAllDocumentHeaders(): List<DocumentHeaderDto>

    fun getDocumentWithPositions(documentId: Long): Document {
        return Document(getDocumentHeaderById(documentId), getDocumentPositionsByHeaderId(documentId))
    }

    @Query("DELETE FROM DocumentHeader WHERE documentHeaderId = :documentId")
    abstract fun deleteDocumentHeader(documentId: Long): Int

    @Query("DELETE FROM DocumentPosition WHERE documentHeaderId = :documentId")
    abstract fun deleteDocumentPositions(documentId: Long): Int

}