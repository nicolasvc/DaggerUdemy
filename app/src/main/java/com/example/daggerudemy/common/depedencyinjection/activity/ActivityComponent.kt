package com.example.daggerudemy.common.depedencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.screens.common.ScreensNavigator
import dagger.Component

/**
 * Aca se determina las funciones las cuales esta determinadas en el modulo
 * y su respectivo objeto de retorno, aca no van con el mismo annotaded de @provides
 */
@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun screensNavigator(): ScreensNavigator

    fun layoutInflater(): LayoutInflater

    fun fragmentManager(): FragmentManager

    fun stackoverflowApi(): StackoverflowApi

    fun activity(): AppCompatActivity

}