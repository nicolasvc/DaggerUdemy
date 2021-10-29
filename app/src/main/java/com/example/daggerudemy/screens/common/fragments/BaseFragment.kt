package com.example.daggerudemy.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.daggerudemy.screens.common.activities.BaseActivity


open class BaseFragment : Fragment() {


    /**
     * Se obtiene la clase compositioRoot a travez de la actividad en la cual esta
     * atada el fragmento y ya que todas las actividades implementan BaseActivity
     * se podra acceder al compositionRoot
     */

    /**
     * Se hace uso de la clase autogenerada por dagger
     */
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityCompositionRoot.newPresentationComponent()

    }

    protected val injector get() = presentationComponent


}