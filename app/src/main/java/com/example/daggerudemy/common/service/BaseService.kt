package com.example.daggerudemy.common.service

import android.app.Service
import com.example.daggerudemy.MyApplication
import com.example.daggerudemy.common.depedencyinjection.service.ServiceModule

/**
 * Clase base que hereda de Service encargado de proveer las bases fundamentales de un servicio
 *
 */
abstract class BaseService : Service() {

    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val serviceComponent by lazy {
        appCompositionRoot.newServiceComponent(ServiceModule(this))
    }

}