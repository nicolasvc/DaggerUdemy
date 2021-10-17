package com.example.daggerudemy.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.daggerudemy.common.dependencyinjection.Injector
import com.example.daggerudemy.common.dependencyinjection.PresentationComponentRoot
import com.example.daggerudemy.screens.common.activities.BaseActivity


open class BaseFragment : Fragment() {


    /**
     * Se obtiene la clase compositioRoot a travez de la actividad en la cual esta
     * atada el fragmento y ya que todas las actividades implementan BaseActivity
     * se podra acceder al compositionRoot
     */
    private val compositionRoot
            by lazy { PresentationComponentRoot((requireActivity() as BaseActivity).activityCompositionRoot) }

    protected val injector get() = Injector(compositionRoot)


}