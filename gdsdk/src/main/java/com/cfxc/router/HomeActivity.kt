package com.cfxc.router

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cfxc.router.dispatcher.DispatcherController


class HomeActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val host = findViewById<FragmentContainerView>(R.id.fragment_nav_host).getFragment<NavHostFragment>()
        navController = host.navController

        DispatcherController.dispatch(navController, intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            DispatcherController.dispatch(navController,it)
        }
    }
}