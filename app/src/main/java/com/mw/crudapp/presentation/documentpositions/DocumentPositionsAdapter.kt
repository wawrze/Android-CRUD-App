package com.mw.crudapp.presentation.documentpositions

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseAdapter
import com.mw.crudapp.database.entities.DocumentPosition
import com.mw.crudapp.utils.TextFormatter
import kotlinx.android.synthetic.main.item_document_position.view.*

class DocumentPositionsAdapter(private var data: List<DocumentPosition>) : BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = DocumentPositionVH(
        inflate(parent, R.layout.item_document_position)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is DocumentPositionVH) {
            holder.bindPosition(data[position])
        }
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<DocumentPosition>) {
        this.data = data
        notifyDataSetChanged()
    }

    class DocumentPositionVH(v: View) : ViewHolder(v) {

        fun bindPosition(position: DocumentPosition) {
            itemView.apply {
                item_document_position_product_name.text = position.productName
                item_document_position_product_amount.text = position.amount.toString()
                item_document_position_product_price.text = TextFormatter.formatNetGrossValue(
                    context,
                    position.netPrice,
                    position.grossPrice
                )
                // TODO change to value
                item_document_position_product_value.text = TextFormatter.formatNetGrossValue(
                    context,
                    position.netPrice,
                    position.grossPrice
                )
            }
        }

    }

}