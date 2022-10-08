package com.example.attendx

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.attendx.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment
    private var isFirstRun: Boolean=false
    private lateinit var sharedPref: SharedPreferences
    private lateinit var authenticationActivityBinding: ActivityAuthenticationBinding
    val KEY_FISRT_RUN_STATUS="FIRST_RUN_STATUS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authenticationActivityBinding=ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(authenticationActivityBinding.root)

        navHost=supportFragmentManager.findFragmentById(R.id.nav_host_logged_out) as NavHostFragment
        navController=navHost.navController
        sharedPref=getSharedPreferences("com.application.attendx_run_mode", Context.MODE_PRIVATE)

        isFirstRun=sharedPref.getBoolean(KEY_FISRT_RUN_STATUS,true)

        checkFirstRun()

    }

    private fun checkFirstRun() {
        if (isFirstRun) {
            sharedPref.edit().putBoolean(KEY_FISRT_RUN_STATUS, false).commit()
        }
        else{
            navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

    }
}