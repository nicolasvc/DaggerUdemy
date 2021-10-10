package com.example.daggerudemy

import android.app.Application
import com.example.daggerudemy.networking.StackoverflowApi
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
     * Propidad que crea un objeto tipo retrofit con la capacidad de realizar consultas a
     * StackOverflow
     */
    val stackoverflowApi : StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    override fun onCreate() {
        super.onCreate()
    }

}