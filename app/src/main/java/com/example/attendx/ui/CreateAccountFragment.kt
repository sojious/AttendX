package com.example.attendx.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentCreateAccountBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateAccountFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var errorTextView: TextView
    private lateinit var progress: ProgressBar
    private var createAccountScreenBinding: FragmentCreateAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        createAccountScreenBinding =
            FragmentCreateAccountBinding.inflate(inflater, container, false)
        navController=findNavController()
        createAccountScreenBinding?.apply {
            progress = progressBar
            errorTextView = textViewErrorMessage
        }
        return createAccountScreenBinding?.root

    }

    override fun onResume() {
        super.onResume()
        setUpButtons()

    }

    private fun setUpButtons() {

        createAccountScreenBinding?.apply {

            buttonCreateAccount.setOnClickListener {
                createAccount()

            }

            textViewSignIn.setOnClickListener {
                navController.navigate(R.id.action_createAccountFragment_to_loginFragment)

            }

        }

    }


    private fun createAccount() {

        val email = createAccountScreenBinding?.editTextEmail?.text.toString()
        val password = createAccountScreenBinding?.editTextPassword?.text.toString()

        if(email!="" && password!=""){

            progress.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                val auth = Firebase.auth

                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    if (auth.currentUser != null) {
                        try{
                            auth.currentUser!!.sendEmailVerification().await()
                            withContext(Dispatchers.Main) {
                                navController.navigate(R.id.action_createAccountFragment_to_accountSuccessFragment)
                            }
                        } catch (e:Exception){
                            withContext(Dispatchers.Main){
                                progress.visibility = View.GONE
                                errorTextView.visibility = View.VISIBLE
                                errorTextView.text=e.localizedMessage
                            }
                        }

                    }
                } catch (e: Exception) {

                    withContext(Dispatchers.Main) {
                        progress.visibility = View.GONE
                        errorTextView.visibility = View.VISIBLE
                        when (e) {
                            is FirebaseAuthUserCollisionException -> {
                                var errorCode = e.errorCode
                                when (errorCode) {
                                    "ERROR_EMAIL_ALREADY_IN_USE" -> {
                                        errorTextView.text =
                                            "The email is associated with another account!"
                                    }
                                    "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
                                        errorTextView.text = "The email provided is already in use !"
                                    }
                                    else -> {
                                        errorTextView.text = e.localizedMessage
                                    }
                                }
                            }

                            is FirebaseNetworkException-> {
                                errorTextView.text = "Error conecting!!Check your internet connection"
                            }
                            else-> {
                                errorTextView.text = e.localizedMessage
                            }

                        }
                    }

                }
            }
        }else{errorTextView.text = "Email/Password cannot be empty !"}

    }

        override fun onDestroyView() {
            super.onDestroyView()
            createAccountScreenBinding = null
        }
}