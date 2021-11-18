package com.example.daggerudemy.screens.questionslist

import android.os.Bundle
import android.util.Log
import com.example.daggerudemy.R
import com.example.daggerudemy.screens.common.ScreensNavigator
import com.example.daggerudemy.screens.common.ScreensNavigatorImpl
import com.example.daggerudemy.screens.common.activities.BaseActivity
import javax.inject.Inject

class QuestionsListActivity : BaseActivity() {


    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        Log.e("QuestionListActivity","$screensNavigator")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

      /**
         Se crea esa validación ya que cuando savedInstanceState sea igual a null
          significa que se inicializara por primera ves la actividad lo que significa que
          se agrega el fragmento y cuando se rote el celular lo cual recreara una nueva instancia
          de la actividad android automaticamnete mantendra hara el commit del fragmento
       */
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, QuestionsListFragment())
                .commit()
        }
    }
}