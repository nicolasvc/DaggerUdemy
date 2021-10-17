package com.example.daggerudemy.common.dependencyinjection

import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import java.lang.reflect.Field

/**
 * Clase encargada de extraer la responsabilidad de la inyeccion a la clase en el cual se usan
 * multiples servicios
 * @param componentRoot clase que contiene todos los servicios necesarios inicializados
 * para asignarlo a los atributos de clases
 */
class Injector(private val componentRoot: PresentationComponentRoot) {

    /**
     * Metodo encargado de injectar las propiedades de las clases que cumplan con el annotation Service
     * @param client tipo any que funciona como una clase que contiene ciertas atributos
     */
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    /**
     * metodo encargado de retornar todos las propiedades que contiene la clase
     * @param client contiene todas las propiedades que el cliente necesita
     */
    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    /**
     * Metodo encargado de validar si el campo tiene el annotation service
     * @param field campo el cual sera validado si contiene la anotacion de @Service
     * @return un boolean que valida si el campo tiene anotacion para la inyeccion
     */
    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service)
                return true
        }
        return false
    }

    /**
     * Metodo encargado de realizar la inyeccion o asignarle un valor al campo
     * @param client parametro encargado de realizar el set a un campo
     * @param field parametro encargado de validar su accesibilidad, asignarle un valor y adicional agregar un valor nuevo
     */
    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    /**
     * Metodo encargado que con base al tipo de clase le asigna un valor que lo extrae
     * del compositionRoot
     * @param type parametro que contiene el tipo de clase
     * @return Any
     */
    private fun getServiceForClass(type: Class<*>?): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return componentRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return componentRoot.screensNavigator
            }

            FetchQuestionsUseCase::class.java -> {
                return componentRoot.fetchQuestionsUseCase
            }

            FetchDetailQuestionUseCase::class.java -> {
                return componentRoot.fetchQuestionDetailsUseCase
            }

            ViewMvcFactory::class.java -> {
                return componentRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type : $type")
            }


        }
    }

}