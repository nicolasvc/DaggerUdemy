package com.example.daggerudemy.common.depedencyinjection.app

import android.app.Application
import com.example.daggerudemy.Constants
import com.example.daggerudemy.common.depedencyinjection.Retrofit1
import com.example.daggerudemy.common.depedencyinjection.Retrofit2
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.networking.UrlProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Clase que contiene las propiedades, inicializaciones de casos de usos
 * globales en la aplicación lo cual permite tener un mejor encapsulamiento de esta necesidad
 * y asi obtener mayor legibilidad  y mantenibilidad.
 *
 * el decorador de UiThread obliga a que cualquier clase que haga uso de la clase debe ser llamada en el
 * UiThread
 *
 * @param Application se pasa con el fin de poder obtener el contexto en los cosos que servicios requieran el uso
 * de context
 */


@Module
class AppModule(val application: Application) {
    /**
     * Propiedad que realiza un Build a un objeto de Retrofit y valida que solo se cree una sola instancia cuando
     * se tenga que usar y asi reducir el tiempo de iniciación de la aplicación
     * Usando la palabra lazy delegate , que se encarga de realizar una inicialización segunra en el Treath
     * y asi cuando se use por primera vez se creara la instancia y cuando se tenga que usar de nuevo se retorna la instancia que
     * se creo inicialmente
     */

    @Provides
    @AppScope
    @Retrofit1
    /**
     * Para obtener una instancia del mismo tipo pero con diferente valor, se debe hacer uso de un qualifer como en este caso seria
     * @Retrofit lo que permite identificar a dagger el provider, el @Named ta bien es valido y tiene el mismo uso que el Qualifer
     * Es preferible usar Qualifer debido a que permite tneer un codigo las robusto, legible
     */
    //@Named("retrofit")
    fun retrofit(urlProvider: UrlProvider) : Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl1())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @AppScope
    //@Retrofit2
    @Named("retrofit2")
    fun retrofit2(urlProvider: UrlProvider) : Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getBaseUrl2())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @AppScope
    fun urlProvider()= UrlProvider()


    @Provides
    fun application() = application


    @Provides
    @AppScope
    fun stackoverflowApi (/*@Named("retrofit")*/@Retrofit1 retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

}