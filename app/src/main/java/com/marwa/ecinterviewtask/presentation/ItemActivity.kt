package com.marwa.ecinterviewtask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.marwa.ecinterviewtask.R
import com.marwa.ecinterviewtask.data.model.Competitions
import com.marwa.ecinterviewtask.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {
    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController
    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_item) as NavHostFragment)
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        navGraph = inflater.inflate(R.navigation.item_navigation)
        if (intent.hasExtra("fragment")) {
            when (intent.getStringExtra("fragment")) {
                "CompetitionsDetailsFragment" -> {
                    val bundle = bundleOf().apply {
                        putParcelable("competition", intent.getParcelableExtra("competition"))
                    }
                    navGraph.setStartDestination(R.id.navigation_competition_details)
                    navController.setGraph(navGraph, bundle)
                }
            }


        }
    }
}