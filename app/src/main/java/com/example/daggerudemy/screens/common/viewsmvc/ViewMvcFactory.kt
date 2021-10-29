package com.example.daggerudemy.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsMvc
import com.example.daggerudemy.screens.questionslist.QuestionListViewMvc
import javax.inject.Inject

/**
 * Clase encargada de retornar un ListViewMvc y asi que este contenga el parametro
 * de runtime del frragmento y esta factory va a soportar dependencias de runtime
 * @param layoutInflater parametro que permite la inicializancion de algunas clases
 */
class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun newQuestionedListViewMvc(parentViewGroup: ViewGroup?): QuestionListViewMvc{
        return QuestionListViewMvc(layoutInflater, parentViewGroup)
    }

    fun newQuestionDetailesViewMvc(parentViewGroup: ViewGroup? ): QuestionDetailsMvc{
        return QuestionDetailsMvc(layoutInflater,parentViewGroup)
    }


}