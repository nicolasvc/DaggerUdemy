package com.example.daggerudemy.common.appinjection

import android.app.Application
import com.example.daggerudemy.networking.StackoverflowApi
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun stackoverflowApi() : StackoverflowApi
}