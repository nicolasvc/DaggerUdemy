package com.example.daggerudemy.networking

import com.google.gson.annotations.SerializedName
import com.example.daggerudemy.questions.Question

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)