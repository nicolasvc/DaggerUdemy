package com.example.daggerudemy.common.activityinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.common.appinjection.AppComponent
import com.example.daggerudemy.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides


/**
 * Esta clase esta creadaa con el find en encapsular el scope del activity con las propiedades
 * que se pueden utilizar para evitar fugas de memoria ya que esta clase se inicializara y destruira
 * con el ciclo de vida de la actividad
 * @param activity se usa para poder inicializar la clase ScreensNavigator  y extraer el el supportFragmentManager
 * @param appComponent se usa para poder obtener todos los atributos necesarios de esta clase e
 * implementarlo en la actividad
 *
 */
@Module
class ActivityModule(
    val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {



    @Provides
    fun activity() = activity

    @Provides
    fun application() = appComponent.application()

    @Provides
    @ActivityScope
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun stackoverflowApi() = appComponent.stackoverflowApi()




}