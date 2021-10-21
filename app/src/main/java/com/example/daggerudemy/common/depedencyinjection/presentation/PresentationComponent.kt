package com.example.daggerudemy.common.depedencyinjection.presentation

import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity
import com.example.daggerudemy.screens.questionslist.QuestionsListFragment
import dagger.Subcomponent


/**
 * En el componente se agrega las clases a las cuales se les requiere realizar una inyeccion
 * a travez de una funcion que recibe como parametro la clase la cual tiene las propiedades
 * que estan siendo inyectadas.
 * Las propiedades que se inyectan deben ser publicas y con la anotacion de @Inject
 */

/**
 * Los componentes pueden tener dependenicas de otros componentes
 */

/**
 * Para crear un SubComponent se debe hacer uso de @Subcomponent esta anotacion
 * no recibe depedencias debido a que se obtendra por todos los servicios expuesto
 * por el padre.
 * El componente padre ActivityComponente expone una fabrica que retorna el componente
 * La fabrica siempre recibe los modulos del subcomponent
 * Los subcomponents tienen acceso a todos los servicios provisto por el padre
 */


@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(fragmentQuestionListaFragment: QuestionsListFragment)

    fun inject(activityQuestionDetail: QuestionDetailsActivity)
}