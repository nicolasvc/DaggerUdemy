package com.example.daggerudemy.questions

import com.example.daggerudemy.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Clase que encapsula la logica del dominio
 * sera conocida como el caso de uso
 */
class FetchQuestionsUseCase @Inject constructor(
    private val stackoverflowApi: StackoverflowApi
) {

    /**
     * Clase sellada que contiene los dos tipos de respuesta exitoso o fallido
     * en el caso de exitoso se hara uso de data class que signifia que exportara la data
     * y en el caso de fallido retornada un objeto
     */
    sealed class Result{
        data class Success(val questions: List<Question>) :Result()
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