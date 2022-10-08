package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentAccountSucessBinding


class AccountSuccessFragment : Fragment() {
    private lateinit var accountSuccessFragmentBinding: FragmentAccountSucessBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        accountSuccessFragmentBinding=FragmentAccountSucessBinding.inflate(inflater,container,false)
        //return inflater.inflate(R.layout.fragment_account_sucess, container, false)
        setUpButton()
        return accountSuccessFragmentBinding.root

    }

    private fun setUpButton() {
        accountSuccessFragmentBinding.btnProceedSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_accountSuccessFragment_to_loginFragment)
        }
    }


}