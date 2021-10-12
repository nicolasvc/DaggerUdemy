package com.example.daggerudemy.common.composition

import androidx.annotation.UiThread
import com.example.daggerudemy.Constants
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase que contiene las propiedades, inicializaciones de casos de usos
 * globales en la aplicación lo cual permite tener un mejor encapsulamiento de esta necesidad
 * y asi obtener mayor legibilidad  y mantenibilidad.
 *
 * el decorador de UiThread obliga a que cualquier clase que haga uso de la clase debe ser llamada en el
 * UiThread
 */


@UiThread
class AppCompositionRoot {
    /**
     * Propiedad que realiza un Build a un objeto de Retrofit y valida que solo se cree una sola instancia cuando
     * se tenga que usar y asi reducir el tiempo de iniciación de la aplicación
     * Usando la palabra lazy delegate , que se encarga de realizar una inicialización segunra en el Treath
     * y asi cuando se use por primera vez se creara la instancia y cuando se tenga que usar de nuevo se retorna la instancia que
     * se creo inicialmente
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Propiedad que crea un objeto tipo retrofit con la capacidad de realizar consultas a
     * StackOverflow
     */
    val stackoverflowApi: StackoverflowApi by lazy { retrofit.create(StackoverflowApi::class.java) }






}