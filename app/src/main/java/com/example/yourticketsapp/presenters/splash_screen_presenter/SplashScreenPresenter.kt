package com.example.yourticketsapp.presenters.splash_screen_presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.data.prefs.REMEMBER_USER
import com.example.yourticketsapp.data.prefs.SharedPreferences
import com.example.yourticketsapp.ui.log_reg_activity.LogRegActivity
import com.example.yourticketsapp.ui.main_activity.MainActivity
import com.example.yourticketsapp.ui.splash_screen_activity.SplashActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenPresenter:MainContract.PresenterActivity {

    private var view: Activity? = null
    private var sp: SharedPreferences? = null

    override fun detachView() {
        view = null
    }

    override fun attachView(activity: Activity) {
        view = activity
    }

    fun initShared() {
        sp = SharedPreferences(view!!)
    }

    fun checkRememberUser() {
        when (sp!!.getPrefBool(REMEMBER_USER)) {
            true -> {
                val intent = Intent(view, MainActivity::class.java)
                view!!.startActivity(intent)
                view!!.finish()
            }
            else -> {
                val intent = Intent(view, LogRegActivity::class.java)
                view!!.startActivity(intent)
                view!!.finish()
            }
        }
    }

}