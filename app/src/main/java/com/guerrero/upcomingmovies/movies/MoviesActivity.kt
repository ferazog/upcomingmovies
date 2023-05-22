package com.guerrero.upcomingmovies.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.guerrero.upcomingmovies.R
import com.guerrero.upcomingmovies.databinding.ActivityMoviesBinding
import com.guerrero.upcomingmovies.shared.setupDailyAlarm

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findNavController(R.id.nav_host_fragment).let { navController ->
            NavigationUI.setupActionBarWithNavController(
                this,
                navController
            )
        }
        setupDailyAlarm(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}
