package com.example.daggerudemy.common.dependencyinjection

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import dagger.Component
import dagger.Provides


/**
 * Funciona como un wrapper del modulo
 */
@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun viewMvcFactory():ViewMvcFactory

    fun dialogsNavigator(): DialogsNavigator

    fun fetchQuestionsUseCase(): FetchQuestionsUseCase

    fun fetchDetailQuestionUseCase(): FetchDetailQuestionUseCase

}