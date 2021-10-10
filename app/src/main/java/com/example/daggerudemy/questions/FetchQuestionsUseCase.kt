package com.example.daggerudemy.questions

import com.example.daggerudemy.Constants
import com.example.daggerudemy.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase que encapsula la logica del dominio
 * sera conocida como el caso de uso
 */
class FetchQuestionsUseCase(
    private val stackoverflowApi: StackoverflowApi
) {

    /**
     * Clase sellada que contiene los dos objetos que puede retornar la consulta a retrofit
     * Exitoso o fallido
     */
    sealed class Result{
        class Success(val questions: List<Question>) :Result()
        object Failure:Result()
    }

    suspend fun fetchLatestQuestion() :Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.lastActiveQuestions(20)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.questions)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                }else{
                    throw t
                }
            }
        }
    }
}