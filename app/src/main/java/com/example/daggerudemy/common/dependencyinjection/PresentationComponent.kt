package com.example.daggerudemy.common.dependencyinjection

import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun viewMvcFactory(): ViewMvcFactory

    fun screensNavigator(): ScreensNavigator

    fun dialogsNavigator(): DialogsNavigator

    fun fetchQuestionsUseCase(): FetchQuestionsUseCase

    fun fetchQuestionDetailsUseCase(): FetchDetailQuestionUseCase
}