package com.example.daggerudemy.screens.questiondetails

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerudemy.R
import com.example.daggerudemy.screens.common.toolbar.MyToolbar

class QuestionDetailsMvc(layoutInflater: LayoutInflater, parent:ViewGroup?) {


    private lateinit var toolbar: MyToolbar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var txtQuestionBody: TextView

    val rootView: View = layoutInflater.inflate(R.layout.layout_question_details, parent, false)
    private val context: Context get() = rootView.context

    interface Listener{
        fun onBackClicked()
    }

    private val listeners = HashSet<Listener>()

    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            for(listener in listeners){
                listener.onBackClicked()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }

    private fun<T: View?> findViewById(@IdRes id:Int):T{
        return rootView.findViewById<T>(id)
    }

    fun registerListener(listener: Listener){
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        listeners.remove(listener)
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