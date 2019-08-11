package com.mw.crudapp.presentation.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    // TODO pass data to adapter
                }
        )
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.new_document
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
        viewModel.saveCustomer(customer).observe(
                viewLifecycleOwner,
                Observer {
                    // TODO some toast
                    viewModel.fetchCustomers()
                }
        )
    }

    override fun deleteCustomer(customerId: Long) {
        viewModel.deleteCustomer(customerId).observe(
                viewLifecycleOwner,
                Observer {
                    // TODO some toast
                    viewModel.fetchCustomers()
                }
        )
    }

}