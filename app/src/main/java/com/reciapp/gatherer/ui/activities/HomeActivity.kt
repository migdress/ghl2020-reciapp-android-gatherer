package com.reciapp.gatherer.ui.activities

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.reciapp.gatherer.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
    }

    private fun initViews() {
        val navController = findNavController(R.id.fragNavHost)
        btmNavView.setupWithNavController(navController)
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, HomeActivity::class.java))
        }
    }
}