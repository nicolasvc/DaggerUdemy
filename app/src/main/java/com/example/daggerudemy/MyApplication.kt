package com.example.daggerudemy

import android.app.Application
import com.example.daggerudemy.common.composition.AppCompositionRoot

/**
 * Cuando se crea una propiedad dentro la clase aplicación permitiria acceder en cualquier
 * momento ya que se vuelve una variable global
 */
class MyApplication: Application() {

    /**
     * Atributo de clase que contiene todas las variables,casos de uso globales/transversales
     * que se usaran en la aplicación y asi permitir acceder mas facilmente y centralizado
     */
   lateinit var appCompositionRoot : AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }

}