package com.example.daggerudemy.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.MyApplication
import com.example.daggerudemy.common.depedencyinjection.activity.ActivityModule
import com.example.daggerudemy.common.depedencyinjection.activity.DaggerActivityComponent
import com.example.daggerudemy.common.depedencyinjection.presentation.DaggerPresentationComponent
import com.example.daggerudemy.common.depedencyinjection.presentation.PresentationModule
/**
 * Clase que sera encargada de definir la actividad base y a su vez poder
 * extraer la clase [com.example.daggerudemy.common.presentation] de la aplicación
 * y asi generar un mejor encapsulamiento
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * Al crear la instancia del activity composition va a permitir
     * crear un activity Scope estoy significa que la instancia solo se va a crear y destruir
     * mientras la actividad este bien, evitando asi fugas de memoria, ya que si se inicializa
     * en el application crear una instancia global lo que permitiria tener fugas
     */
    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityCompositionRoot by lazy {
        DaggerActivityComponent.builder()
            .appComponent(appCompositionRoot)
            .activityModule(ActivityModule(this))
            .build()
    }
    /**
     * Se pasa como parametro la clase AppCompositionRoot a la clase de
     * ActivityCompositioRoot el cual permite solo tener una sola instancia
     * de composition root y asi tener una mejor abstracción
     */
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent(activityCompositionRoot)
            .presentationModule(PresentationModule())
            .build()
    }

    protected val injector get() = presentationComponent


}