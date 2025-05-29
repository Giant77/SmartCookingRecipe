package com.example.smartcookingrecipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartcookingrecipe.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
            when (destination.id) {
                R.id.navigation_home,
                R.id.navigation_ingredients,
                R.id.navigation_nutrition,
                R.id.navigation_recipe -> {
                    bottomNavView.visibility = View.VISIBLE
                    }
                else -> {
                    bottomNavView.visibility = View.GONE
                }
            }
        }

        navView.setupWithNavController(navController)
    }
}