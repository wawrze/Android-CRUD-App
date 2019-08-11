package com.mw.crudapp.injection.components

import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.injection.modules.DatabaseModule
import com.mw.crudapp.injection.modules.NavigationModule
import com.mw.crudapp.presentation.MainActivity
import com.mw.crudapp.presentation.adddocument.AddDocumentViewModel
import com.mw.crudapp.presentation.customers.CustomersViewModel
import com.mw.crudapp.presentation.documentlist.DocumentsViewModel
import com.mw.crudapp.presentation.documentpositions.DocumentPositionsViewModel
import com.mw.crudapp.presentation.editdocument.EditDocumentViewModel
import com.mw.crudapp.presentation.products.ProductsViewModel
import dagger.Component

@Component(
        modules = [
            DatabaseModule::class,
            NavigationModule::class
        ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(documentsViewModel: DocumentsViewModel)
    fun inject(documentPositionsViewModel: DocumentPositionsViewModel)
    fun inject(addDocumentViewModel: AddDocumentViewModel)
    fun inject(editDocumentViewModel: EditDocumentViewModel)
    fun inject(customersViewModel: CustomersViewModel)
    fun inject(productsViewModel: ProductsViewModel)

}