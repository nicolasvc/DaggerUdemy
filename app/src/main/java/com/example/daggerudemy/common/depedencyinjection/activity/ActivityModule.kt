package com.example.daggerudemy.common.depedencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.ScreensNavigatorImpl
import dagger.Binds
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

/**
 * Nuestro object pasa a hacer una clase abstracta debido a que para hacer uso de la anotación @Binds requeire una función abstracta
 * lo que implica que nuestra clase pase a volver abstracta y asi permitir la debida implementación
 */
@Module
abstract class ActivityModule {
    /**
    Al crear un tipo companion significa que se creara unos metodos staticos esta es uan forma optima de crear
    las instancias en dagger
     */


    /**
     * Para poder hacer uso de la inyección de una interfaz en las clase se debe poner en el metodo provider
     * que retornar una interfaz del tipo que deseamos y a su vez retornarmos es la clase que implementa la interfaz
     */
    /* @Provides
     @ActivityScope
     fun screenNavigator(activity: AppCompatActivity): ScreensNavigator =
         ScreensNavigatorImpl(activity)*/

    /**
     * A travez del automatic discovery cuando se solicite una interfaz se recibe como parametro
     * la clase que implementa dicha interfaz para asi retornar la interfaz como tal.
     * adicionalmente se hace uso de @Binds para que asi funcione como @Provides lo cual permite que debe proveer cuando se solicite
     * la interfaz
     */
    @ActivityScope
    @Binds
    abstract fun screenNavigator(screensNavigatorImpl: ScreensNavigatorImpl): ScreensNavigator


    companion object {
        @Provides
        fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
            LayoutInflater.from(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }

}