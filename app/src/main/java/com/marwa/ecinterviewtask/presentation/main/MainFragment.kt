package com.marwa.ecinterviewtask.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marwa.ecinterviewtask.R
import com.marwa.ecinterviewtask.databinding.FragmentHomeBinding
import com.marwa.ecinterviewtask.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.navigation_home)
                R.id.navigation_competitions -> navController.navigate(R.id.navigation_competitions)
                R.id.navigation_favorites -> navController.navigate(R.id.navigation_favorites)
                R.id.navigation_today -> navController.navigate(R.id.navigation_today)
                else -> navController.navigate(R.id.navigation_more)
            }
            return@setOnItemSelectedListener true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}