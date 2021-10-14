package com.example.daggerudemy.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.activities.BaseActivity
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import kotlinx.coroutines.*

class QuestionDetailsActivity : BaseActivity(),QuestionDetailsMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var questionId: String
    private lateinit var questionDetailsMvc: QuestionDetailsMvc
    private lateinit var fetchDetailQuestionUseCase: FetchDetailQuestionUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailsMvc = compositionRoot.viewMvcFactory.newQuestionDetailsViewMvc(null)
        setContentView(questionDetailsMvc.rootView)
        fetchDetailQuestionUseCase = compositionRoot.fetchDetailQuestionUseCase
        dialogsNavigator = compositionRoot.dialogsNavigator
        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
        screensNavigator = compositionRoot.screensNavigator
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
                when(result){
                    is FetchDetailQuestionUseCase.Result.Success->{
                        questionDetailsMvc.showData(result.questionBody)
                    }
                    is FetchDetailQuestionUseCase.Result.Failure->  onFetchFailed()
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