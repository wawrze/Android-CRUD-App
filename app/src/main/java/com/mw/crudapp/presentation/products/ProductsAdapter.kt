package com.mw.crudapp.presentation.products

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseAdapter
import com.mw.crudapp.database.entities.Product
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_customer.view.*
import kotlinx.android.synthetic.main.item_new_customer.view.*
import kotlinx.android.synthetic.main.item_new_product.view.*

class ProductsAdapter(private val actions: ProductsActions) : BaseAdapter() {

    companion object {
        private const val ADD_PRODUCT_TYPE = 0
        private const val NEW_PRODUCT_TYPE = 1
        private const val EDIT_PRODUCT_TYPE = 2
        private const val PRODUCT_TYPE = 3
    }

    var data = ArrayList<Product>()
    private var addNewCustomer = false
    private var editedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (viewType) {
        ADD_PRODUCT_TYPE -> AddProductVH(inflate(parent, R.layout.item_add))
        NEW_PRODUCT_TYPE -> NewProductVH(inflate(parent, R.layout.item_new_product))
        EDIT_PRODUCT_TYPE -> EditProductVH(inflate(parent, R.layout.item_new_product))
        else -> ProductVH(inflate(parent, R.layout.item_customer))
    }

    override fun getItemViewType(position: Int): Int = when {
        addNewCustomer && position == 0 -> NEW_PRODUCT_TYPE
        position == 0 -> ADD_PRODUCT_TYPE
        (position - 1) == editedPosition -> EDIT_PRODUCT_TYPE
        else -> PRODUCT_TYPE
    }

    override fun getItemCount(): Int = data.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is AddProductVH -> holder.bind(this)
            is NewProductVH -> holder.bindNewProduct(this)
            is EditProductVH -> holder.bindEditProduct(this, data[position - 1])
            is ProductVH -> holder.bindProduct(this, data[position - 1], position - 1)
        }
    }

    fun showAddNewProductItem(state: Boolean) {
        addNewCustomer = state
        notifyDataSetChanged()
    }

    private fun editItem(position: Int) {
        editedPosition = position
        notifyDataSetChanged()
    }

    fun addNewProduct(product: Product) {
        actions.saveProduct(product)
        addNewCustomer = false
        notifyDataSetChanged()
    }

    fun saveEditedProduct(product: Product) {
        actions.saveProduct(product)
        editedPosition = -1
        notifyDataSetChanged()
    }

    fun removeProduct(productId: Long) {
        actions.deleteProduct(productId)
        notifyDataSetChanged()
    }

    fun updateData(data: ArrayList<Product>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ProductVH(v: View) : ViewHolder(v) {

        fun bindProduct(adapter: ProductsAdapter, product: Product, position: Int) {
            itemView.apply {
                item_customer_name.text = product.productName
                item_customer_edit.setOnClickListener {
                    adapter.editItem(position)
                }
                item_customer_remove.setOnClickListener {
                    adapter.removeProduct(product.productId)
                }
            }
        }

    }

    class NewProductVH(v: View) : ViewHolder(v) {

        fun bindNewProduct(adapter: ProductsAdapter) {
            itemView.apply {
                item_new_product_cancel.setOnClickListener {
                    item_new_product_input.text.clear()
                    adapter.showAddNewProductItem(false)
                }
                item_new_product_save.setOnClickListener {
                    val productName = item_new_product_input.text.toString()
                    item_new_product_input.text.clear()
                    adapter.addNewProduct(Product(productName))
                }
            }
        }
    }

    class EditProductVH(v: View) : ViewHolder(v) {

        fun bindEditProduct(adapter: ProductsAdapter, product: Product) {
            itemView.apply {
                item_new_product_input.setText(product.productName)
                item_new_product_cancel.setOnClickListener {
                    item_new_product_input.text.clear()
                    adapter.editItem(-1)
                }
                item_new_product_save.setOnClickListener {
                    val productName = item_new_customer_input.text.toString()
                    item_new_product_input.text.clear()
                    adapter.saveEditedProduct(Product(product.productId, productName))
                }
            }
        }

    }

    class AddProductVH(v: View) : ViewHolder(v) {

        fun bind(adapter: ProductsAdapter) {
            itemView.apply {
                item_add_text.text = resources.getString(R.string.add_product)
                item_add_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_product))
                setOnClickListener {
                    adapter.showAddNewProductItem(true)
                }
            }
        }

    }

}