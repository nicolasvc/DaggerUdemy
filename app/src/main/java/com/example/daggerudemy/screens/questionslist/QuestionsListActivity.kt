package com.example.daggerudemy.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerudemy.Constants
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchQuestionsUseCase
import com.example.daggerudemy.questions.Question
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.dialogs.DialogsNavigator
import com.example.daggerudemy.screens.common.dialogs.ServerErrorDialogFragment
import com.example.daggerudemy.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : AppCompatActivity(), QuestionListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var viewMvc: QuestionListViewMvc
    private var isDataLoaded = false
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = QuestionListViewMvc(LayoutInflater.from(this),null)
        fetchQuestionsUseCase = FetchQuestionsUseCase()
        dialogsNavigator = DialogsNavigator(supportFragmentManager)
        screensNavigator = ScreensNavigator(this)
        setContentView(viewMvc.rootView)
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
            try{
                val result = fetchQuestionsUseCase.fetchLatestQuestion()
                when(result){
                    is FetchQuestionsUseCase.Result.Success ->{
                        viewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionsUseCase.Result.Failure-> onFetchFailed()
                }
            }finally {
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