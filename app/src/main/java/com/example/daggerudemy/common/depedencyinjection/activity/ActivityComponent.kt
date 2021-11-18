package com.example.daggerudemy.common.depedencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.common.depedencyinjection.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Aca se determina las funciones las cuales esta determinadas en el modulo
 * y su respectivo objeto de retorno, aca no van con el mismo annotaded de @provides
 */
@ActivityScope
//@Component(dependencies=[AppComponent::class], modules = [ActivityModule::class])
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

    /**
     * Aca se hace uso del patron Builder.
     * Se hace uso de Subcomponent debido a que este Component es un Subcomponent
     * si fuera un Component, se haria uso del Component
     */
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance fun activity(activity:AppCompatActivity):Builder
        fun build():ActivityComponent
    }

}