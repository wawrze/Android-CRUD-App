package com.mw.crudapp.presentation.customers

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseAdapter
import com.mw.crudapp.database.entities.Customer
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_customer.view.*
import kotlinx.android.synthetic.main.item_new_customer.view.*

class CustomersAdapter(private val actions: CustomersActions) : BaseAdapter() {

    companion object {
        private const val ADD_CUSTOMER_TYPE = 0
        private const val NEW_CUSTOMER_TYPE = 1
        private const val EDIT_CUSTOMER_TYPE = 2
        private const val CUSTOMER_TYPE = 3
    }

    var data = ArrayList<Customer>()
    private var addNewCustomer = false
    private var editedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when (viewType) {
        ADD_CUSTOMER_TYPE -> AddPositionVH(inflate(parent, R.layout.item_add))
        NEW_CUSTOMER_TYPE -> NewPositionVH(inflate(parent, R.layout.item_new_customer))
        EDIT_CUSTOMER_TYPE -> EditPositionVH(inflate(parent, R.layout.item_new_customer))
        else -> CustomerVH(inflate(parent, R.layout.item_customer))
    }

    override fun getItemViewType(position: Int): Int = when {
        addNewCustomer && position == 0 -> NEW_CUSTOMER_TYPE
        position == 0 -> ADD_CUSTOMER_TYPE
        (position - 1) == editedPosition -> EDIT_CUSTOMER_TYPE
        else -> CUSTOMER_TYPE
    }

    override fun getItemCount(): Int = data.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is AddPositionVH -> holder.bind(this)
            is NewPositionVH -> holder.bindNewPosition(this)
            is EditPositionVH -> holder.bindEditPosition(this, data[position - 1])
            is CustomerVH -> holder.bindPosition(this, data[position - 1], position - 1)
        }
    }

    fun showAddNewCustomerItem(state: Boolean) {
        addNewCustomer = state
        notifyDataSetChanged()
    }

    private fun editItem(position: Int) {
        editedPosition = position
        notifyDataSetChanged()
    }

    fun addNewCustomer(customer: Customer) {
        actions.saveCustomer(customer)
        addNewCustomer = false
        notifyDataSetChanged()
    }

    fun saveEditedCustomer(customer: Customer) {
        actions.saveCustomer(customer)
        editedPosition = -1
        notifyDataSetChanged()
    }

    fun removeCustomer(customerId: Long) {
        actions.deleteCustomer(customerId)
        notifyDataSetChanged()
    }

    fun updateData(data: ArrayList<Customer>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CustomerVH(v: View) : ViewHolder(v) {

        fun bindPosition(adapter: CustomersAdapter, customer: Customer, position: Int) {
            itemView.apply {
                item_customer_name.text = customer.customerName
                item_customer_edit.setOnClickListener {
                    adapter.editItem(position)
                }
                item_customer_remove.setOnClickListener {
                    adapter.removeCustomer(customer.customerId)
                }
            }
        }

    }

    class NewPositionVH(v: View) : ViewHolder(v) {

        fun bindNewPosition(adapter: CustomersAdapter) {
            itemView.apply {
                item_new_customer_cancel.setOnClickListener {
                    item_new_customer_input.text.clear()
                    adapter.showAddNewCustomerItem(false)
                }
                item_new_customer_save.setOnClickListener {
                    val customerName = item_new_customer_input.text.toString()
                    item_new_customer_input.text.clear()
                    adapter.addNewCustomer(Customer(customerName))
                }
            }
        }
    }

    class EditPositionVH(v: View) : ViewHolder(v) {

        fun bindEditPosition(
                adapter: CustomersAdapter,
                customer: Customer
        ) {
            itemView.apply {
                item_new_customer_input.setText(customer.customerName)
                item_new_customer_cancel.setOnClickListener {
                    item_new_customer_input.text.clear()
                    adapter.editItem(-1)
                }
                item_new_customer_save.setOnClickListener {
                    val customerName = item_new_customer_input.text.toString()
                    item_new_customer_input.text.clear()
                    adapter.saveEditedCustomer(Customer(customer.customerId, customerName))
                }
            }
        }

    }

    class AddPositionVH(v: View) : ViewHolder(v) {

        fun bind(adapter: CustomersAdapter) {
            itemView.apply {
                item_add_text.text = resources.getString(R.string.add_customer)
                item_add_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_add_customer))
                setOnClickListener {
                    adapter.showAddNewCustomerItem(true)
                }
            }
        }

    }

}