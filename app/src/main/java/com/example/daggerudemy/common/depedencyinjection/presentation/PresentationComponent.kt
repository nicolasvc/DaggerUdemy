package com.example.daggerudemy.common.depedencyinjection.presentation

import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity
import com.example.daggerudemy.screens.questionslist.QuestionsListFragment
import dagger.Component

/**
 * En el componente se agrega las clases a las cuales se les requiere realizar una inyeccion
 * a travez de una funcion que recibe como parametro la clase la cual tiene las propiedades
 * que estan siendo inyectadas.
 * Las propiedades que se inyectan deben ser publicas y con la anotacion de @Inject
 */
@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(fragmentQuestionListaFragment: QuestionsListFragment)

    fun inject(activityQuestionDetail:QuestionDetailsActivity)
}