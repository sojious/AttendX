package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var homeFragmentBinding: FragmentHomeBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
         homeFragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpButtons()
        return homeFragmentBinding?.root
    }


    private fun setUpButtons() {
        homeFragmentBinding?.apply {
            val navController = findNavController()

            btnCreateOrg.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_createOrganizationFragment)
            }
            btnJoinOrg.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_joinOrganizationFragment)
            }
            containerAddFingerprint.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_addFingerprintFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeFragmentBinding=null
    }
}