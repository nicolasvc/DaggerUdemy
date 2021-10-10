package com.example.daggerudemy.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerudemy.R
import com.example.daggerudemy.screens.common.toolbar.MyToolbar
import com.example.daggerudemy.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailsMvc(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewMvc<QuestionDetailsMvc.Listener>(
        layoutInflater, parent,
        R.layout.layout_question_details
    ) {


    private val toolbar: MyToolbar
    private val swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView


    interface Listener {
        fun onBackClicked()
    }

    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)
        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        //Se le agrega una acción a un objeto con base en el listener que se ha pasado
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackClicked()
            }
        }
        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }


    fun showData(questionBody: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

}