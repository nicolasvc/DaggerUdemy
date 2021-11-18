package com.example.daggerudemy.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.common.depedencyinjection.activity.ActivityScope
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity
import dagger.Provides
import javax.inject.Inject

/**
 * Clase que implementa ScreensNavigator para la definir la navegación de la aplicación
 */
class ScreensNavigatorImpl @Inject constructor(private val activity: AppCompatActivity):ScreensNavigator {


    override fun navigateBack(){
        activity.onBackPressed()
    }

    override fun toQuestionDetails(questionId:String){
        QuestionDetailsActivity.start(activity, questionId)
    }
}