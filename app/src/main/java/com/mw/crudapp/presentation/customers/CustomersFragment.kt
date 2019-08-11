package com.mw.crudapp.presentation.customers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.database.entities.Customer
import kotlinx.android.synthetic.main.fragment_customers.*

class CustomersFragment : BaseFragment(), CustomersActions {

    private lateinit var viewModel: CustomersViewModel
    private var adapter: CustomersAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(CustomersViewModel::class.java)
        return inflater.inflate(R.layout.fragment_customers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSetup()
        viewModel.fetchCustomers().observe(
                viewLifecycleOwner,
                Observer {
                    adapter?.updateData(it as ArrayList)
                }
        )
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.customers
        navigationBack = true
    }

    private fun recyclerSetup() {
        if (adapter == null) {
            adapter = CustomersAdapter(this)
        }
        fragment_customers_recycler.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        fragment_customers_recycler.adapter = adapter
    }

    override fun saveCustomer(customer: Customer) {
        activity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.window?.currentFocus?.applicationWindowToken, 0)
        }
        viewModel.saveCustomer(customer).observe(
                viewLifecycleOwner,
                Observer {
                    if (it) {
                        viewModel.fetchCustomers()
                    } else {
                        Toast.makeText(context, R.string.customer_save_error, Toast.LENGTH_LONG).show()
                    }
                }
        )
    }

    override fun deleteCustomer(customerId: Long) {
        viewModel.deleteCustomer(customerId).observe(
                viewLifecycleOwner,
                Observer {
                    if (it) {
                        viewModel.fetchCustomers()
                    } else {
                        Toast.makeText(context, R.string.customer_delete_error, Toast.LENGTH_LONG).show()
                    }
                }
        )
    }

}