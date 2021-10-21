package com.example.daggerudemy.common.depedencyinjection.activity

import com.example.daggerudemy.common.depedencyinjection.app.AppComponent
import com.example.daggerudemy.common.depedencyinjection.presentation.PresentationComponent
import com.example.daggerudemy.common.depedencyinjection.presentation.PresentationModule
import dagger.Component

/**
 * Aca se determina las funciones las cuales esta determinadas en el modulo
 * y su respectivo objeto de retorno, aca no van con el mismo annotaded de @provides
 */
@ActivityScope
@Component(dependencies=[AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(presentationModule: PresentationModule):PresentationComponent

}