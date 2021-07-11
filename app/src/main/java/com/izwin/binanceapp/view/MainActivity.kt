 package com.izwin.binanceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.izwin.binanceapp.R
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(bottomNavigationView, Navigation.findNavController(this , R.id.host_fragment))
    }
}