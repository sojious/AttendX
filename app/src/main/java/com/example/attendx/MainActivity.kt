package com.example.attendx

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.attendx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  userViewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        navHost=supportFragmentManager.findFragmentById(R.id.nav_host_logged_in) as NavHostFragment
        navController=navHost.navController
        mainActivityBinding.bottomNavigationView.setupWithNavController(navController)
        //Check if app is opened for the first time
       checkAuthStatus()

    }

    private fun checkAuthStatus() {
        if(userViewModel.currentUser==null || !userViewModel.currentUser!!.isEmailVerified){
            startActivity(Intent(this,AuthenticationActivity::class.java))
            finish()
        }

    }


}