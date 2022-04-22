package com.example.yourticketsapp.ui.log_reg_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import com.example.yourticketsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogRegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_reg)
    }
}