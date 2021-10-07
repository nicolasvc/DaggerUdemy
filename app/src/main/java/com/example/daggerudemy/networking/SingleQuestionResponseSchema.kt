package com.example.daggerudemy.networking

import com.google.gson.annotations.SerializedName
import com.example.daggerudemy.questions.QuestionWithBody

data class SingleQuestionResponseSchema(@SerializedName("items") val questions: List<QuestionWithBody>) {
    val question: QuestionWithBody get() = questions[0]
}