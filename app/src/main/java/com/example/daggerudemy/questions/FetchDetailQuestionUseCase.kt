package com.example.daggerudemy.questions

import com.example.daggerudemy.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


/**
 * La clase recibe como parametro a retrofit el cual permite generar una DIAP(
 * dependency inject archital pattern)
 */
class FetchDetailQuestionUseCase(
    private val stackoverflowApi: StackoverflowApi
) {

    /**
     * Clase sellada que contiene los dos objetos que puede retornar la consulta a retrofit
     * Exitoso o fallido
     */
    sealed class Result{
        class Success(val questionBody: String) :Result()
        object Failure:Result()
    }


    suspend fun fetchDetailQuestion(questionId:String):Result{
        return withContext(Dispatchers.IO){
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question.body
                    return@withContext Result.Success(questionBody)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                }else{
                    return@withContext Result.Failure
                }
            }
        }
    }

}