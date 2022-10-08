package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attendx.R
import com.example.attendx.databinding.FragmentAddFingerprintBinding


class AddFingerprintFragment : Fragment() {


    private var addFingerprintBinding: FragmentAddFingerprintBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_fingerprint, container, false)
        addFingerprintBinding = FragmentAddFingerprintBinding.inflate(inflater, container, false)
        return addFingerprintBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addFingerprintBinding=null
    }
}