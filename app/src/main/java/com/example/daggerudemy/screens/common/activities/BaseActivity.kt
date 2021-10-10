package com.example.daggerudemy.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.MyApplication


/**
 * Clase que sera encargada de definir la actividad base y a su vez poder
 * extraer la clase [com.example.daggerudemy.common.composition] de la aplicación
 * y asi generar un mejor encapsulamiento
 */
open class BaseActivity : AppCompatActivity() {

    val compositionRoot get() = (application as MyApplication).appCompositionRoot

}