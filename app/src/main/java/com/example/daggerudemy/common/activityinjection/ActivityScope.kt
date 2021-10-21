package com.example.daggerudemy.common.activityinjection

import javax.inject.Scope

/**
 * Esta anotación permite que se mantenga una instancia de un servicio solo una unica vez mientras sea solicitado
 * el servicio y asi evitar multiples instancias del servicio
 */
@Scope
annotation class ActivityScope()
