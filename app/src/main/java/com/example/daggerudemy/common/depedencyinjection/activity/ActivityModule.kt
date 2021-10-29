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
@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {
    @Provides
    fun activity() = activity

    @Provides
    @ActivityScope
    fun screenNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager





}