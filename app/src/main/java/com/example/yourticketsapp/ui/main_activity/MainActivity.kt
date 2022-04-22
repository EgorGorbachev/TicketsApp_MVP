package com.example.yourticketsapp.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.yourticketsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById((R.id.mainFragmentContainerView)) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNav.animate()
        bottomNav.setupWithNavController(navController)
    }
}