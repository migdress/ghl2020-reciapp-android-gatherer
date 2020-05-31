package com.reciapp.gatherer.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.reciapp.gatherer.R
import com.reciapp.gatherer.ui.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
    }

    private fun initViews() {
        val navController = findNavController(R.id.fragNavHost)
        btmNavView.setupWithNavController(navController)

        tbHome.subtitle = homeViewModel.getUserName()
    }

    companion object {
        fun launch(from: Context) {
            from.startActivity(Intent(from, HomeActivity::class.java))
        }
    }
}