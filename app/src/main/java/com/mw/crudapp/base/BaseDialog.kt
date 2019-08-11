package com.mw.crudapp.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mw.crudapp.R

abstract class BaseDialog : DialogFragment() {

    fun show(fragmentManager: FragmentManager?) {
        fragmentManager?.let { super.show(it, "") }
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(FEATURE_NO_TITLE)
        val margin = resources.getDimension(R.dimen.regular_margin).toInt()
        dialog.window?.setBackgroundDrawable(
            InsetDrawable(ColorDrawable(Color.TRANSPARENT), margin)
        )
        return dialog
    }

}