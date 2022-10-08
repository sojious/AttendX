package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentCreateOrganizationBinding
import com.example.attendx.databinding.FragmentHomeBinding


class CreateOrganizationFragment : Fragment() {


    private var createOrganizationFragmentBinding: FragmentCreateOrganizationBinding?=null
    private lateinit var navController:NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_create_organization, container, false)
        navController=findNavController()
        createOrganizationFragmentBinding = FragmentCreateOrganizationBinding.inflate(inflater, container, false)
        createOrganizationFragmentBinding?.imageButton?.setOnClickListener(){
            navController.navigate(R.id.action_createOrganizationFragment_to_mapsFragment)
        }
        return createOrganizationFragmentBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        createOrganizationFragmentBinding=null
    }
}