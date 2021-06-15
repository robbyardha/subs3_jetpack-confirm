package com.ardhacodes.subs1_jetpack.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ardhacodes.subs1_jetpack.R
import com.ardhacodes.subs1_jetpack.ui.main.MainViewModel
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import com.ardhacodes.subs1_jetpack.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = activityMainBinding.root
        setContentView(view)

        viewModelProviderMainConfig()
        TabViewPagerConfigController()
//
//        setupBottomNav()

    }

    fun viewModelProviderMainConfig()
    {
        viewModel = ViewModelProvider(this@MainActivity, factory)[MainViewModel::class.java]
    }

    fun TabViewPagerConfigController()
    {
        var mov_fragment_layout = R.id.movie_fragment
        var tv_fragment_layout = R.id.tv_fragment
        var fav_fragment_layout = R.id.favorite_fragment

        val navView: BottomNavigationView = findViewById(R.id.tab_menu_navigation)
        val navController = findNavController(R.id.host_main_navigation)

        val appBarConfiguration = AppBarConfiguration.Builder(
            mov_fragment_layout,
            tv_fragment_layout,
            fav_fragment_layout
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.elevation = 0f
        
    }
    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}

