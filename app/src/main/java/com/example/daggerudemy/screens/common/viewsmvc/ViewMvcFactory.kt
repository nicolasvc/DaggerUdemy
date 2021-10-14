package com.example.daggerudemy.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsMvc
import com.example.daggerudemy.screens.questionslist.QuestionListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun newQuestionedListViewMvc(parentViewGroup: ViewGroup?): QuestionListViewMvc{
        return QuestionListViewMvc(layoutInflater, parentViewGroup)
    }

    fun newQuestionDetailsViewMvc(parentViewGroup: ViewGroup?): QuestionDetailsMvc{
        return QuestionDetailsMvc(layoutInflater,parentViewGroup)
    }


}