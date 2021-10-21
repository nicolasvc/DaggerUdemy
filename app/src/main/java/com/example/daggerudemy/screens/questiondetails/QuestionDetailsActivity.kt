package com.example.daggerudemy.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.activities.BaseActivity
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var questionId: String

    @Inject
    lateinit var fetchDetailQuestionUseCase: FetchDetailQuestionUseCase

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var questionDetailsMvc: QuestionDetailsMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        questionDetailsMvc = viewMvcFactory.newQuestionDetailesViewMvc(null)


        // Obtener data que se pasa entre actividades
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
        setContentView(questionDetailsMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        questionDetailsMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        questionDetailsMvc.removeListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            questionDetailsMvc.showProgressIndication()
            try {
                val result = fetchDetailQuestionUseCase.fetchDetailQuestion(questionId)
                when (result) {
                    is FetchDetailQuestionUseCase.Result.Success -> {
                        questionDetailsMvc.showData(result.questionBody)
                    }
                    is FetchDetailQuestionUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questionDetailsMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }


    override fun onBackClicked() {
        screensNavigator.navigateBack()
    }
}