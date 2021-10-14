package com.example.daggerudemy.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.common.composition.ActivityCompositionRoot
import com.example.daggerudemy.common.composition.AppCompositionRoot
import com.example.daggerudemy.common.composition.PresentationCompositionRoot


/**
 * Clase que sera encargada de definir la actividad base y a su vez poder
 * extraer la clase [com.example.daggerudemy.common.composition] de la aplicación
 * y asi generar un mejor encapsulamiento
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * Al crear la instancia del activity composition va a permitir
     * crear un activity Scope estoy significa que la instancia solo se va a crear y destruir
     * mientras la actividad este bien, evitando asi fugas de memoria, ya que si se inicializa
     * en el application crear una instancia global lo que permitiria tener fugas
     */
    private val appCompositionRoot: AppCompositionRoot = AppCompositionRoot()

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    /**
     * Se pasa como parametro la clase AppCompositionRoot a la clase de
     * ActivityCompositioRoot el cual permite solo tener una sola instancia
     * de composition root y asi tener una mejor abstracción
     */
    protected val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot) }


}