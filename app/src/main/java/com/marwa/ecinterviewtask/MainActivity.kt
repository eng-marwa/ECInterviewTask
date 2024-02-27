package com.marwa.ecinterviewtask

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.marwa.ecinterviewtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment)
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        navGraph = inflater.inflate(R.navigation.app_navigation)
        navigateToSplash()
    }

    private fun navigateToSplash() {
        navGraph.setStartDestination(R.id.navigation_splash)
        navController.graph = navGraph
    }
}