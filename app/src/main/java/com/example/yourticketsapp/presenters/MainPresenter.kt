package com.example.yourticketsapp.presenters

import android.view.View
import androidx.fragment.app.Fragment
import com.example.yourticketsapp.contract.MainContract
import javax.inject.Inject

class MainPresenter @Inject constructor(): MainContract.PresenterFragment{

    override fun attachView(fragment: Fragment, mView: View) {}
    override fun detachView(){}
}