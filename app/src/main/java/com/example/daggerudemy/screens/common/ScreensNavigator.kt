package com.example.daggerudemy.screens.common

import android.app.Activity
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: Activity) {


    fun navigateBack(){
        activity.onBackPressed()
    }

    fun toQuestionDetails(questionId:String){
        QuestionDetailsActivity.start(activity, questionId)
    }
}