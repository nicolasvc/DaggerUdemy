package com.example.daggerudemy.common.depedencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides


/**
 * Esta clase esta creadaa con el find en encapsular el scope del activity con las propiedades
 * que se pueden utilizar para evitar fugas de memoria ya que esta clase se inicializara y destruira
 * con el ciclo de vida de la actividad
 * @param activity se usa para poder inicializar la clase ScreensNavigator  y extraer el el supportFragmentManager
 *
 */


/**
 * Debido a que se removio la instancia del activity de nuestro modulo se puede volver la class en un object
 * permitiendo que asi todos los metodos en el objeto sean tratados como static y no hacer uso del companion object
 */
@Module
object ActivityModule{
    /**
    Al crear un tipo companion significa que se creara unos metodos staticos esta es uan forma optima de crear
     las instancias en dagger
     */

        @Provides
        @ActivityScope
        fun screenNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

        @Provides
        fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

}