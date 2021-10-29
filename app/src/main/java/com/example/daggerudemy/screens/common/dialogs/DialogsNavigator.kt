package com.example.daggerudemy.screens.common.dialogs

import androidx.fragment.app.FragmentManager
import javax.inject.Inject

/**
 * Clase encargada de encapsular una sola responsabilidad la cual es mostrar
 * un mensaje de error
 * @param fragmentManager que se usa para poder mostrar un error del servidor
 */
class DialogsNavigator @Inject constructor(private val fragmentManager: FragmentManager) {

    fun showServerErrorDialog(){
        fragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

}