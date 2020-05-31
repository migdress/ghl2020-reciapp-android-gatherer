package com.reciapp.gatherer.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.reciapp.gatherer.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence,
            i: Int,
            i2: Int,
            i3: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence,
            i: Int,
            i2: Int,
            i3: Int
        ) {
        }

        override fun afterTextChanged(editable: Editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        tieUserName.addTextChangedListener(textWatcher)
        tieUserPassword.addTextChangedListener(textWatcher)

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

    fun checkFieldsForEmptyValues() {
        val user: String = tieUserName.text.toString()
        val password: String = tieUserPassword.text.toString()
        btnLogin.isEnabled = !(user.isEmpty() || password.isEmpty())
    }
}
