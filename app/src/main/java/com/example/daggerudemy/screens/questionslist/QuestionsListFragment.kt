package com.example.daggerudemy.screens.questionslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.questions.Question
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.ScreensNavigatorImpl
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.fragments.BaseFragment
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

/**
Se pasa toda la logica a este fragmento  que contenia la vista en si misma
 */
class QuestionsListFragment : BaseFragment(), QuestionListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var viewMvc: QuestionListViewMvc
    private var isDataLoaded = false

    @Inject
    lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        Log.e("QuestionListFragment","$screensNavigator")
    }

    /**
     * Para poder inicializar nuestro viewMvc se le debe pasa el container de la actividad que
     * lo esta llamando y asi asignarle un root view, Pero esto es una mala practica
     * debido a que este metodo se ejecuta en runTime multiples veces lo que implica que pueda generar
     * crash por que lo sea crea una factory encargada de tener esa responsabilidad y poder generar la depedencia
     * en el compositionRoot. ESTE NUNCA SE LE DEBEN PASAR PARAMETROS DE RUNTIME
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewMvc = viewMvcFactory.newQuestionedListViewMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.removeListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchQuestionsUseCase.fetchLatestQuestion()
                when (result) {
                    is FetchQuestionsUseCase.Result.Success -> {
                        viewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(clickedQuestion: Question) {
        screensNavigator.toQuestionDetails(clickedQuestion.id)
    }


}