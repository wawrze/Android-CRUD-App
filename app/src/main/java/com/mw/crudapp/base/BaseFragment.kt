package com.mw.crudapp.base

import android.content.Context
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.mw.crudapp.injection.components.Injector
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var navigate: NavController

    @Inject
    lateinit var actionBar: ActionBar

    protected var navigationBack: Boolean = false
        set(value) {
            field = value
            showNavigationBack(value)
        }

    protected var toolbarTitleRes: Int = 0
        set(value) {
            field = value
            toolbarTitle = getString(field)
        }

    private var toolbarTitle: String = ""
        set(value) {
            field = value
            actionBar.title = value
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        @Suppress("LeakingThis")
        Injector.inject(this)
    }

    private fun showNavigationBack(isShow: Boolean) {
        actionBar.apply {
            setDisplayShowHomeEnabled(isShow)
            setDisplayHomeAsUpEnabled(isShow)
            setDisplayUseLogoEnabled(isShow)
        }
    }

}