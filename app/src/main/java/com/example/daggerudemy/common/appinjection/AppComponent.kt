package com.example.daggerudemy.common.appinjection

import android.app.Application
import com.example.daggerudemy.networking.StackoverflowApi
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun stackoverflowApi() : StackoverflowApi
}