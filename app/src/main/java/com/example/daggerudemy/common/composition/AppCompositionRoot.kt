package com.example.daggerudemy.common.composition

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
 */
class AppCompositionRoot {

    /**
     * Propiedad que realiza un Build a un objeto de Retrofit
     */
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    /**
     * Propiedad que crea un objeto tipo retrofit con la capacidad de realizar consultas a
     * StackOverflow
     */
    private val stackoverflowApi : StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    /**
     * Se hace uso de la palabra get para que cuando se se llame esta propiedad se cree la instancia y a su vez se destruya
     * lo que va a evitar fugas de memoria y posibles crash
     */
    val fetchQuestionsUseCase get()  = FetchQuestionsUseCase(stackoverflowApi)

    val fetchDetailQuestionUseCase get() = FetchDetailQuestionUseCase(stackoverflowApi)

}