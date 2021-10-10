package com.example.daggerudemy

import android.app.Application
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Cuando se crea una propiedad dentro la clase aplicación permitiria acceder en cualquier
 * momento ya que se vuelve una variable global
 */
class MyApplication: Application() {

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

    val fetchQuestionsUseCase get()  = FetchQuestionsUseCase(stackoverflowApi)

    val fetchDetailQuestionUseCase get() = FetchDetailQuestionUseCase(stackoverflowApi)

    override fun onCreate() {
        super.onCreate()
    }

}