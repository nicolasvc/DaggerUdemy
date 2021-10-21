package com.example.daggerudemy.common.depedencyinjection.app

import android.app.Application
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
}