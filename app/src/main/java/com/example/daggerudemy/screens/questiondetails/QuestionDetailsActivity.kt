package com.example.daggerudemy.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.daggerudemy.R
import com.example.daggerudemy.Constants
import com.example.daggerudemy.networking.StackoverflowApi
import com.example.daggerudemy.questions.FetchDetailQuestionUseCase
import com.example.daggerudemy.screens.common.dialogs.ServerErrorDialogFragment
import com.example.daggerudemy.screens.common.toolbar.MyToolbar
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDetailsActivity : AppCompatActivity(),QuestionDetailsMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var questionId: String

    private lateinit var questionDetailsMvc: QuestionDetailsMvc

    private lateinit var fetchDetailQuestionUseCase: FetchDetailQuestionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailsMvc = QuestionDetailsMvc(LayoutInflater.from(this),null)
        setContentView(questionDetailsMvc.rootView)
        fetchDetailQuestionUseCase = FetchDetailQuestionUseCase()

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
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
        supportFragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss()
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
       onBackPressed()
    }
}