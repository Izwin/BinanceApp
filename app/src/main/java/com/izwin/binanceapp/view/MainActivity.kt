 package com.izwin.binanceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.izwin.binanceapp.R
import com.izwin.binanceapp.view.adapters.BinanceAdapter
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener
import com.izwin.binanceapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(bottomNavigationView, Navigation.findNavController(this , R.id.host_fragment))


    }

}