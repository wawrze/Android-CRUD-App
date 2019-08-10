package com.mw.crudapp.injection.components

import com.mw.crudapp.base.BaseFragment
import com.mw.crudapp.injection.modules.DatabaseModule
import com.mw.crudapp.injection.modules.NavigationModule
import com.mw.crudapp.presentation.MainActivity
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

}