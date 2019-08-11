package com.mw.crudapp.presentation.documentpositions

import androidx.lifecycle.MutableLiveData
import com.mw.crudapp.base.BaseViewModel
import com.mw.crudapp.database.dao.DocumentDao
import com.mw.crudapp.database.models.DocumentPositionDto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DocumentPositionsViewModel : BaseViewModel() {

    @Inject
    lateinit var documentDao: DocumentDao

    private val positions: MutableLiveData<List<DocumentPositionDto>> = MutableLiveData()

    fun fetchPositions(documentId: Long): MutableLiveData<List<DocumentPositionDto>> {
        Observable.fromCallable { documentDao.getDocumentPositionsByHeaderId(documentId) }
                .onErrorReturn { ArrayList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    positions.postValue(it)
                }
                .addToDisposables()
        return positions
    }

}