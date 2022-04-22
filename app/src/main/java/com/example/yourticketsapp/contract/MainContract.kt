package com.example.yourticketsapp.contract

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

interface MainContract {

    interface MainView {
        fun showToast(message: String)
    }

    interface PresenterFragment {
        fun attachView(fragment: Fragment, mView: View)
        fun detachView()
    }

    interface PresenterActivity {
        fun attachView(activity: Activity)
        fun detachView()
    }
}