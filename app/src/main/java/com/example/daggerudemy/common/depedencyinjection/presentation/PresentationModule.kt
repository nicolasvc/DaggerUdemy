package com.example.daggerudemy.common.depedencyinjection.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.daggerudemy.common.depedencyinjection.activity.ActivityComponent
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides


/**
 * Esta clase esta creada con el fin de poder agregar los servicios necesarios que necesita la actividad
 * ya que asi se mantendra la clase activityCompositionRoot de una forma mas limpia y clara
 * @param activityModule clase que contiene los parametros necesarios para poder acceder e inicializar los servicios
 */
@Module
class PresentationModule {


    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionsUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionDetailsUseCase(stackoverflowApi: StackoverflowApi) = FetchDetailQuestionUseCase(stackoverflowApi)
}