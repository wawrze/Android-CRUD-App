package com.mw.crudapp.injection.modules

import androidx.navigation.findNavController
import com.mw.crudapp.R
import com.mw.crudapp.presentation.MainActivity
import dagger.Module
import dagger.Provides

@Module
class NavigationModule(private val activity: MainActivity) {

    @Provides
    fun provideNavController() = activity.findNavController(R.id.nav_host_fragment)

    @Provides
    fun provideActionBar() = activity.actionBar!!

}