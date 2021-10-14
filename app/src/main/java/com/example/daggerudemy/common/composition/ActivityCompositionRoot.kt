package com.example.daggerudemy.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory


/**
 * Esta clase esta creadaa con el find en encapsular el scope del activity con las propiedades
 * que se pueden utilizar para evitar fugas de memoria ya que esta clase se inicializara y destruira
 * con el ciclo de vida de la actividad
 * @param activity se usa para poder inicializar la clase ScreensNavigator  y extraer el el supportFragmentManager
 * @param appCompositionRoot se usa para poder obtener todos los atributos necesarios de esta clase e
 * implementarlo en la actividad
 *
 */
class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val layoutInflater get() =  LayoutInflater.from(activity)

    val viewMvcFactory get() =  ViewMvcFactory(layoutInflater)

    /**
     * Se extrae el supportFragmentManager de la actividad que se le esta pasando
     * */

    private val fragmentManager get() = activity.supportFragmentManager

    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi

    /**
     * Se hace uso de la palabra get para que cuando se se llame esta propiedad se cree la instancia y a su vez se destruya
     * lo que va a evitar fugas de memoria y posibles crash
     */
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)

    val fetchDetailQuestionUseCase get() = FetchDetailQuestionUseCase(stackoverflowApi)

    val dialogsNavigator by lazy {
        DialogsNavigator(fragmentManager)
    }
}