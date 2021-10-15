package com.example.daggerudemy.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.screens.common.ScreensNavigator


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

    val application get() = appCompositionRoot.application

    val layoutInflater: LayoutInflater get() =  LayoutInflater.from(activity)

    /**
     * Se extrae el supportFragmentManager de la actividad que se le esta pasando
     * */

    val fragmentManager get() = activity.supportFragmentManager

    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi




}