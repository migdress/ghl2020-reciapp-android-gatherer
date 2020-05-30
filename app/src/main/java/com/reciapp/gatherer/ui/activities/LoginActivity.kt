package com.reciapp.gatherer.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reciapp.gatherer.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            HomeActivity.launch(this)
            finish()
        }
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, LoginActivity::class.java))
        }
    }
}