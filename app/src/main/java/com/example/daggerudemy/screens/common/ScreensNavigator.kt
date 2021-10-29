package com.example.daggerudemy.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: AppCompatActivity) {


    fun navigateBack(){
        activity.onBackPressed()
    }

    fun toQuestionDetails(questionId:String){
        QuestionDetailsActivity.start(activity, questionId)
    }
}