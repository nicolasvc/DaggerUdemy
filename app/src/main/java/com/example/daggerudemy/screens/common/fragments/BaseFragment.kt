package com.example.daggerudemy.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.daggerudemy.common.dependencyinjection.DaggerPresentationComponent
import com.example.daggerudemy.common.dependencyinjection.Injector
import com.example.daggerudemy.common.dependencyinjection.PresentationModule
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
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)


}