package com.example.daggerudemy.common.dependencyinjection

import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity
import com.example.daggerudemy.screens.questionslist.QuestionsListFragment

/**
 * Clase encargada de extraer la responsabilidad de la inyeccion a la clase en el cual se usan
 * multiples servicios
 * @param compositionRoot clase que contiene todos los servicios necesarios inicializados
 * para asignarlo a los atributos de clases
 */
class Injector(private val compositionRoot: PresentationCompositionRoot) {


    fun inject(fragment: QuestionsListFragment) {
        fragment.dialogsNavigator = compositionRoot.dialogsNavigator
        fragment.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        fragment.screensNavigator = compositionRoot.screensNavigator
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(activity: QuestionDetailsActivity) {
        activity.dialogsNavigator = compositionRoot.dialogsNavigator
        activity.fetchDetailQuestionUseCase = compositionRoot.fetchDetailQuestionUseCase
        activity.screensNavigator = compositionRoot.screensNavigator
        activity.viewMvcFactory = compositionRoot.viewMvcFactory
    }
}