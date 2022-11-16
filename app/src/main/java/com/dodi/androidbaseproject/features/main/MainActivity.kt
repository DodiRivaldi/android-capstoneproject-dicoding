package com.dodi.androidbaseproject.features.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dodi.androidbaseproject.MyApp
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.ActivityMainBinding
import com.dodi.core.abstraction.base.BaseActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var navController: NavController

    @ExperimentalCoroutinesApi
    override fun ActivityMainBinding.oncreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this@MainActivity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
    }

    override fun observerViewModel() {

    }

    companion object {
        fun navigate(activity: Activity) {
            Intent(activity, MainActivity::class.java).apply {
                activity.startActivity(this)
            }
        }
    }
}