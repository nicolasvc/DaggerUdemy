package com.example.daggerudemy.common.dependencyinjection

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.dialogs.ServerErrorDialogFragment
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides


/**
 * Esta clase esta creada con el fin de poder agregar los servicios necesarios que necesita la actividad
 * ya que asi se mantendra la clase activityCompositionRoot de una forma mas limpia y clara
 * @param activityCompositionRoot clase que contiene los parametros necesarios para poder acceder e inicializar los servicios
 */
@Module
class PresentationModule(private val activityCompositionRoot: ActivityCompositionRoot) {

    @Provides
    fun layoutInflater() = activityCompositionRoot.layoutInflater

    @Provides
    fun fragmentManager() = activityCompositionRoot.fragmentManager

    @Provides
    fun stackoverflowApi() = activityCompositionRoot.stackoverflowApi

    @Provides
    fun screensNavigator() = activityCompositionRoot.screensNavigator

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    /**
     * Se hace uso de la palabra get para que cuando se se llame esta propiedad se cree la instancia y a su vez se destruya
     * lo que va a evitar fugas de memoria y posibles crash
     */
    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionsUseCase(stackoverflowApi)

    @Provides
    fun fetchDetailQuestionUseCase(stackoverflowApi: StackoverflowApi) = FetchDetailQuestionUseCase(stackoverflowApi)
}