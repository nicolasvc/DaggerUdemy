package com.example.daggerudemy.common.composition

import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory


/**
 * Esta clase esta creada con el fin de poder agregar los servicios necesarios que necesita la actividad
 * ya que asi se mantendra la clase activityCompositionRoot de una forma mas limpia y clara
 * @param activityCompositionRoot clase que contiene los parametros necesarios para poder acceder e inicializar los servicios
 */

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val layoutInflater get() = activityCompositionRoot.layoutInflater

    private val fragmentManager get() = activityCompositionRoot.fragmentManager

    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi

    val screensNavigator get() = activityCompositionRoot.screensNavigator

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    /**
     * Se hace uso de la palabra get para que cuando se se llame esta propiedad se cree la instancia y a su vez se destruya
     * lo que va a evitar fugas de memoria y posibles crash
     */
    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)

    val fetchDetailQuestionUseCase get() = FetchDetailQuestionUseCase(stackoverflowApi)
}