package com.mw.crudapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mw.crudapp.R
import com.mw.crudapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainPageFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_main_documents_section.setOnClickListener {
            navigate.navigate(MainPageFragmentDirections.toDocuments())
        }
        fragment_main_customers_section.setOnClickListener {
            navigate.navigate(MainPageFragmentDirections.toCustomers())
        }
//        fragment_main_products_section.setOnClickListener {
//            navigate.navigate(MainPageFragmentDirections.toProducts())
//        }
    }

    override fun onResume() {
        super.onResume()
        toolbarTitleRes = R.string.app_name
        navigationBack = false
    }

}