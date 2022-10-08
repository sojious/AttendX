package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var welcomeScreenBinding: FragmentWelcomeBinding? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        welcomeScreenBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return welcomeScreenBinding?.root

    }

    override fun onResume() {
        super.onResume()
        navigateIntoApp()

    }

    private fun navigateIntoApp() {

        welcomeScreenBinding?.textStartButton?.setOnClickListener{
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        welcomeScreenBinding=null
    }



}