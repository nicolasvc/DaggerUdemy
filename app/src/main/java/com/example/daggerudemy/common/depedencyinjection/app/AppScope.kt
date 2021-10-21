package com.example.daggerudemy.common.depedencyinjection.app

import javax.inject.Scope


/**
 * Esta anotación permite que se mantenga una instancia de un servicio solo una unica vez mientras sea solicitado
 * el servicio y asi evitar multiples instancias del servicio
 */
@Scope
annotation class AppScope()
