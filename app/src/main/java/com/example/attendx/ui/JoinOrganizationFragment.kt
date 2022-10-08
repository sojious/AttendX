package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attendx.R
import com.example.attendx.databinding.FragmentHomeBinding
import com.example.attendx.databinding.FragmentJoinOrganizationBinding

class JoinOrganizationFragment : Fragment() {

    private var joinOrganizationFragmentBinding: FragmentJoinOrganizationBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_join_organization, container, false)
        joinOrganizationFragmentBinding = FragmentJoinOrganizationBinding.inflate(inflater, container, false)
        return joinOrganizationFragmentBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        joinOrganizationFragmentBinding=null
    }
}