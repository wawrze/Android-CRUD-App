package com.mw.crudapp.presentation.documentlist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseAdapter
import com.mw.crudapp.database.entities.DocumentHeader

class DocumentsAdapter(private var data: List<DocumentHeader>, private val actions: DocumentActions) : BaseAdapter() {

    companion object {
        private const val FOOTER_TYPE = 0
        private const val DOCUMENT_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (viewType) {
        FOOTER_TYPE -> FooterVH(inflate(parent, R.layout.item_add))
        else -> DocumentVH(inflate(parent, R.layout.item_document))
    }

    override fun getItemViewType(position: Int): Int = if (position == data.size) {
        FOOTER_TYPE
    } else {
        DOCUMENT_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is DocumentVH -> holder.bindDocument(data[position], actions)
            is FooterVH -> holder.bindFooter(actions)
        }
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    fun updateData(data: List<DocumentHeader>) {
        this.data = data
        notifyDataSetChanged()
    }

    class DocumentVH(v: View) : ViewHolder(v) {

        fun bindDocument(document: DocumentHeader, actions: DocumentActions) {

        }

    }

    class FooterVH(v: View) : ViewHolder(v) {

        fun bindFooter(adapter: DocumentActions) {

        }

    }

}