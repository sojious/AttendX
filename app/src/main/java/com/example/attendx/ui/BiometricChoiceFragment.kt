package com.example.attendx.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.attendx.AuthenticationActivity
import com.example.attendx.MainActivity
import com.example.attendx.databinding.FragmentBiometricChoiceBinding

class BiometricChoiceFragment : Fragment() {

    private var biometricChoiceBinding:FragmentBiometricChoiceBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biometricChoiceBinding=FragmentBiometricChoiceBinding.inflate(inflater,container,false)
        return biometricChoiceBinding?.root
    }

    override fun onResume() {
        super.onResume()
        setUpChoices()
    }

    private fun setUpChoices() {
        biometricChoiceBinding?.apply {
            choiceContainer1.setOnClickListener{
                startActivity(Intent(activity, MainActivity::class.java) )
                activity?.finish()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        biometricChoiceBinding=null
    }
}