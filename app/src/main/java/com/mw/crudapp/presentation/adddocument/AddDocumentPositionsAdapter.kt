package com.mw.crudapp.presentation.adddocument

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseAdapter
import com.mw.crudapp.database.entities.Product
import com.mw.crudapp.database.models.DocumentPositionDto
import com.mw.crudapp.utils.TextFormatter
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_document_position.view.*
import kotlinx.android.synthetic.main.item_new_document_position.view.*

class AddDocumentPositionsAdapter : BaseAdapter() {

    companion object {
        private const val ADD_POSITION_TYPE = 0
        private const val NEW_POSITION_TYPE = 1
        private const val EDIT_POSITION_TYPE = 2
        private const val POSITION_TYPE = 3
    }

    val data = ArrayList<DocumentPositionDto>()
    private var addNewPosition = false
    private var editedPosition: Int = -1
    private var products = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (viewType) {
        ADD_POSITION_TYPE -> AddPositionVH(inflate(parent, R.layout.item_add))
        NEW_POSITION_TYPE -> NewPositionVH(inflate(parent, R.layout.item_new_document_position))
        EDIT_POSITION_TYPE -> EditPositionVH(inflate(parent, R.layout.item_new_document_position))
        else -> PositionVH(inflate(parent, R.layout.item_document_position))
    }

    override fun getItemViewType(position: Int): Int = when {
        addNewPosition && position == 0 -> NEW_POSITION_TYPE
        position == 0 -> ADD_POSITION_TYPE
        (position - 1) == editedPosition -> EDIT_POSITION_TYPE
        else -> POSITION_TYPE
    }

    override fun getItemCount(): Int = data.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is AddPositionVH -> holder.bind(this)
            is NewPositionVH -> holder.bindNewPosition(this, products)
            is EditPositionVH -> holder.bindEditPosition(this, data[position - 1], products, position - 1)
            is PositionVH -> holder.bindPosition(this, data[position - 1], position - 1)
        }
    }

    private fun showAddNewPositionItem(state: Boolean) {
        addNewPosition = state
        notifyDataSetChanged()
    }

    private fun editItem(position: Int) {
        editedPosition = position
        notifyDataSetChanged()
    }

    fun addNewPosition(documentPosition: DocumentPositionDto) {
        data.add(0, documentPosition)
        addNewPosition = false
        notifyDataSetChanged()
    }

    fun editPosition(position: Int, documentPosition: DocumentPositionDto) {
        data[position] = documentPosition
        editedPosition = -1
        notifyDataSetChanged()
    }

    fun removePosition(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateProducts(products: ArrayList<Product>) {
        this.products = products
    }

    class PositionVH(v: View) : ViewHolder(v) {

        fun bindPosition(adapter: AddDocumentPositionsAdapter, documentPosition: DocumentPositionDto, position: Int) {
            itemView.apply {
                item_document_position_product_name.text = documentPosition.productName
                item_document_position_product_amount.text = documentPosition.amount.toString()
                item_document_position_product_price.text = TextFormatter.formatNetGrossValue(
                        context,
                        documentPosition.netPrice,
                        documentPosition.grossPrice
                )
                item_document_position_product_value.text = TextFormatter.formatNetGrossValue(
                        context,
                        documentPosition.netValue,
                        documentPosition.grossValue
                )
                item_document_position_edit.apply {
                    visibility = View.VISIBLE
                    setOnClickListener { adapter.editItem(position) }
                }
                item_document_position_remove.apply {
                    visibility = View.VISIBLE
                    setOnClickListener { adapter.removePosition(position) }
                }
            }
        }

    }

    class NewPositionVH(v: View) : ViewHolder(v) {

        fun bindNewPosition(adapter: AddDocumentPositionsAdapter, products: ArrayList<Product>) {
            itemView.apply {
                context?.let { context ->
                    val spinnerAdapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        products.map { customer -> customer.productName })
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    item_new_document_position_product_spinner.adapter = spinnerAdapter
                }
                item_new_document_position_cancel.setOnClickListener {
                    clearInputs()
                    adapter.showAddNewPositionItem(false)
                }
                item_new_document_position_add.setOnClickListener {
                    val productId = products[item_new_document_position_product_spinner.selectedItemPosition].productId
                    val productName =
                        products[item_new_document_position_product_spinner.selectedItemPosition].productName

                    val amount = item_new_document_position_amount_input.text.toString().toDoubleOrNull()
                        ?: 0.0
                    val netPrice = item_new_document_position_net_price_input.text.toString().toDoubleOrNull()
                        ?: 0.0
                    val grossPrice =
                        item_new_document_position_gross_price_input.text.toString().toDoubleOrNull()
                            ?: 0.0
                    clearInputs()
                    adapter.addNewPosition(
                        DocumentPositionDto(
                            0,
                            0,
                            productId,
                            productName,
                            amount,
                            netPrice,
                            grossPrice
                        )
                    )
                }
            }
        }

        private fun clearInputs() {
            itemView.apply {
                item_new_document_position_product_spinner.setSelection(0)
                item_new_document_position_amount_input.text.clear()
                item_new_document_position_net_price_input.text.clear()
                item_new_document_position_gross_price_input.text.clear()
            }
        }

    }

    class EditPositionVH(v: View) : ViewHolder(v) {

        fun bindEditPosition(
            adapter: AddDocumentPositionsAdapter,
            documentPosition: DocumentPositionDto,
            products: ArrayList<Product>,
            position: Int
        ) {
            itemView.apply {
                context?.let { context ->
                    val spinnerAdapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        products.map { customer -> customer.productName })
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    item_new_document_position_product_spinner.adapter = spinnerAdapter
                }
                item_new_document_position_product_spinner.setSelection(
                    products.indexOf(Product(documentPosition.productId, documentPosition.productName))
                )
                item_new_document_position_amount_input.setText(documentPosition.amount.toString())
                item_new_document_position_net_price_input.setText(documentPosition.netPrice.toString())
                item_new_document_position_gross_price_input.setText(documentPosition.grossPrice.toString())
                item_new_document_position_cancel.setOnClickListener {
                    clearInputs()
                    adapter.editItem(-1)
                }
                item_new_document_position_add.setOnClickListener {
                    val productId = products[item_new_document_position_product_spinner.selectedItemPosition].productId
                    val productName =
                        products[item_new_document_position_product_spinner.selectedItemPosition].productName
                    val amount = item_new_document_position_amount_input.text.toString().toDoubleOrNull()
                            ?: 0.0
                    val netPrice = item_new_document_position_net_price_input.text.toString().toDoubleOrNull()
                            ?: 0.0
                    val grossPrice =
                            item_new_document_position_gross_price_input.text.toString().toDoubleOrNull()
                                    ?: 0.0
                    clearInputs()
                    adapter.editPosition(
                            position,
                            DocumentPositionDto(
                                    0,
                                    0,
                                productId,
                                    productName,
                                    amount,
                                    netPrice,
                                    grossPrice
                            )
                    )
                }
            }
        }

        private fun clearInputs() {
            itemView.apply {
                item_new_document_position_product_spinner.setSelection(0)
                item_new_document_position_amount_input.text.clear()
                item_new_document_position_net_price_input.text.clear()
                item_new_document_position_gross_price_input.text.clear()
            }
        }

    }

    class AddPositionVH(v: View) : ViewHolder(v) {

        fun bind(adapter: AddDocumentPositionsAdapter) {
            itemView.apply {
                item_add_text.text = resources.getString(R.string.add_position)
                setOnClickListener {
                    adapter.showAddNewPositionItem(true)
                }
            }
        }

    }

}