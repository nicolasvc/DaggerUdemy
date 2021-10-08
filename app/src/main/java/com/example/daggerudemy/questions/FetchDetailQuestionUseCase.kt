package com.example.daggerudemy.questions

import com.example.daggerudemy.Constants
import com.example.daggerudemy.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchDetailQuestionUseCase {

    /**
     * Clase sellada que contiene los dos objetos que puede retornar la consulta a retrofit
     * Exitoso o fallido
     */
    sealed class Result{
        class Success(val questionBody: String) :Result()
        object Failure:Result()
    }
    // init retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)


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