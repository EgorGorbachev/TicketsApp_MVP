package com.example.yourticketsapp.ui.splash_screen_activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yourticketsapp.R
import com.example.yourticketsapp.presenters.splash_screen_presenter.SplashScreenPresenter
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var presenter:SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        presenter = SplashScreenPresenter()
        presenter.attachView(this)
        presenter.initShared()

        presenter.checkRememberUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}