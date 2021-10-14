package com.example.daggerudemy.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.daggerudemy.screens.questionslist.QuestionListViewMvc

/**
 * Clase encargada de retornar un ListViewMvc y asi que este contenga el parametro
 * de runtime del frragmento y esta factory va a soportar dependencias de runtime
 *
 */
class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun newQuestionedListViewMvc(parentViewGroup: ViewGroup?): QuestionListViewMvc{
        return QuestionListViewMvc(layoutInflater, parentViewGroup)
    }


}