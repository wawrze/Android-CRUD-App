package com.mw.crudapp.presentation

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mw.crudapp.R
import com.mw.crudapp.injection.components.Injector
import com.mw.crudapp.injection.modules.DatabaseModule
import com.mw.crudapp.injection.modules.NavigationModule

class MainActivity : AppCompatActivity() {

    val actionBar: ActionBar?
        get() = supportActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.injector
            .databaseModule(DatabaseModule(this))
            .navigationModule(NavigationModule(this))
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}