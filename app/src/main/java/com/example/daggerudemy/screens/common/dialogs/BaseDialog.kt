package com.example.daggerudemy.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.daggerudemy.screens.common.activities.BaseActivity

open class BaseDialog : DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityCompositionRoot.newPresentationComponent()

    }

    protected val injector get() = presentationComponent

}