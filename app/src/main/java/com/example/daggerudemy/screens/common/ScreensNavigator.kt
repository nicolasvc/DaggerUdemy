package com.example.daggerudemy.screens.common

/**
 * Interfaz encargada de definir las acciones de navegación en la aplicacion
 */
interface ScreensNavigator {

    fun navigateBack()


    fun toQuestionDetails(questionId:String)
}