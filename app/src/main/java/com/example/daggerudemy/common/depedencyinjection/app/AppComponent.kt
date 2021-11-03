package com.example.daggerudemy.common.depedencyinjection.app

import android.app.Application
import com.example.daggerudemy.common.depedencyinjection.activity.ActivityComponent
import com.example.daggerudemy.common.depedencyinjection.activity.ActivityModule
import com.example.daggerudemy.common.depedencyinjection.service.ServiceComponent
import com.example.daggerudemy.common.depedencyinjection.service.ServiceModule
import com.example.daggerudemy.networking.StackoverflowApi
import dagger.Component

/**
 * El componente es el encargado de exponer lo servicios a travez de funciones y el modulo
 * es el encargado de inicializar los objetos para que se puedan obtener
 */
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun stackoverflowApi() : StackoverflowApi

    /**
     * Si se dejara asi genera error debido a que dagger o puede crear el componente de
     * ActivityComponent ya que el encargado sera el Builder
     */
    //fun newActivityComponent(activityModule: ActivityModule):ActivityComponent

    fun newActivityComponentBuilder():ActivityComponent.Builder


    fun newServiceComponent(serviceModule:ServiceModule):ServiceComponent
}