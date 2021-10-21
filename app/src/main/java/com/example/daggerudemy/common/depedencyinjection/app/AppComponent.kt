package com.example.daggerudemy.common.depedencyinjection.app

import android.app.Application
import com.example.daggerudemy.networking.StackoverflowApi
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun stackoverflowApi() : StackoverflowApi
}