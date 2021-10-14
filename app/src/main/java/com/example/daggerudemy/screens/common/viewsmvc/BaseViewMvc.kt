package com.example.daggerudemy.screens.common.viewsmvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes


/**
 * Clase encargada de centralizar la vista base del MVC
 * @param layoutInflater parametro encargado de hacer el inflate a la vista
 * @param parent parametro que permite el inflate a partir del layout que se pasa anteriormente
 * @param layoutId parametro tipo layoutId encargado de inflate la vista
 */
open class BaseViewMvc<LISTENER_TYPE>(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    @LayoutRes private val layoutId:Int
        ) {

    //region Properties
    protected val listeners = HashSet<LISTENER_TYPE>()
    //obtener un root view a partir de un LayoutInflater
    val rootView: View = layoutInflater.inflate(layoutId, parent, false)
    //Obtener el contexto del rootview
    protected val context: Context get() = rootView.context
    //endregion

    /**
     * @param listener recibe un una interfaz tipo listener y la agrega en el HashSet
     */
    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    /**
     * @param listener recibe un una interfaz tipo listener y lo elimina del HashSet
     */
    fun removeListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }

    /**
     * Metodo encargado de retornar una vista de forma dinamica y asi reducir el codigo
     * @param id como parametro un entero que funciona como un IdRES
     * @return Un objeto tipo T de la vista (Recycler, TextView,Etc...)
     * */
    protected fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

}