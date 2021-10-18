package com.example.daggerudemy

import android.app.Application
import com.example.daggerudemy.common.appinjection.AppModule
import com.example.daggerudemy.common.appinjection.DaggerAppComponent

/**
 * Cuando se crea una propiedad dentro la clase aplicación permitiria acceder en cualquier
 * momento ya que se vuelve una variable global
 */
class MyApplication: Application() {

    /**
     * Atributo de clase que contiene todas las variables,casos de uso globales/transversales
     * que se usaran en la aplicación y asi permitir acceder mas facilmente y centralizado
     * Ya haciendo la migración a dagger se permite obtener la misma inicialización de mejor forma
     * y asi omitir la creación o inicializacion la variable en el onCreate
     */
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}