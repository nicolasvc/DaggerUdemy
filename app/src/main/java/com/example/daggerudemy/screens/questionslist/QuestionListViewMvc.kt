package com.example.daggerudemy.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerudemy.R
import com.example.daggerudemy.questions.Question
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.List

/**
 * Clase encargada de renderizar la vista de una actividad en especifico
 */
class QuestionListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) {


    /**
     * Interfaz encargada de definir la funcionalidad cuando se quiera refrescar
     */
    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(clickedQuestion: Question)
    }

    private val swipeRefresh: SwipeRefreshLayout
    private val recyclerView: RecyclerView
    private val questionsAdapter: QuestionsAdapter

    val rootView: View = layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
    private val context: Context get() = rootView.context

    //HashSet es una lista sin orden que no contiene elementos duplicados
    private val listeners = HashSet<Listener>()

    init {

        // init pull-down-to-refresh
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        // init recycler view
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        questionsAdapter = QuestionsAdapter { clickedQuestion ->
            for (listener in listeners) {
                listener.onQuestionClicked(clickedQuestion)
            }
        }
        recyclerView.adapter = questionsAdapter

    }


    fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindData(questions)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    /**
     * Metodo encargado de retornar una vista de forma dinamica y asi reducir el codigo
     * @param id como parametro un entero que funciona como un IdRES
     * @return Un objeto tipo T de la vista (Recycler, TextView,Etc...)
     * */
    private fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }

    /**
     * @param listener recibe un una interfaz tipo listener y la agrega en el HashSet
     */
    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    /**
     * @param listener recibe un una interfaz tipo listener y lo elimina del HashSet
     */
    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }




    class QuestionsAdapter(
        private val onQuestionClickListener: (Question) -> Unit
    ) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

        private var questionsList: List<Question> = ArrayList(0)

        inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(questions: List<Question>) {
            questionsList = ArrayList(questions)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_question_list_item, parent, false)
            return QuestionViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            holder.title.text = questionsList[position].title
            holder.itemView.setOnClickListener {
                onQuestionClickListener.invoke(questionsList[position])
            }
        }

        override fun getItemCount(): Int {
            return questionsList.size
        }

    }
}