package com.example.attendx.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.attendx.R
import com.example.attendx.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var progress: ProgressBar
    private lateinit var errorTextView: TextView
    private  var loginScreenBinding:FragmentLoginBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginScreenBinding = FragmentLoginBinding.inflate(inflater, container, false)
        navController=findNavController()
        loginScreenBinding?.apply {
            errorTextView=textViewErrorMessage
            progress=progressBar
        }
        setUpButtons()
        return loginScreenBinding?.root

    }


    private fun setUpButtons() {

        loginScreenBinding?.apply {

            buttonSignIn.setOnClickListener{
                //findNavController().navigate(R.id.action_loginFragment_to_biometricChoiceFragment)

                signInUser()

            }

            textViewCreateAccount.setOnClickListener{
                loginScreenBinding?.editTextPassword?.text?.clear()
                navController.navigate(R.id.action_loginFragment_to_createAccountFragment)
            }
        }
    }

    private fun signInUser() {


        val email = loginScreenBinding?.editTextEmail?.text.toString()
        val password = loginScreenBinding?.editTextPassword?.text.toString()

        if(email !="" && password!=""){
            progress.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                val auth = Firebase.auth

                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    if (auth.currentUser != null && auth.currentUser!!.isEmailVerified) {
                        withContext(Dispatchers.Main) {
                            navController.navigate(R.id.biometricChoiceFragment)
                        }
                    }else{
                        withContext(Dispatchers.Main) {

                            progress.visibility = View.GONE
                            errorTextView.visibility = View.VISIBLE
                            errorTextView.text="You are yet to verify your email.\nKindly check your mailbox for a verification link"
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        progress.visibility = View.GONE
                        errorTextView.visibility = View.VISIBLE
                        when (e) {
                            is FirebaseAuthInvalidUserException -> {
                                var errorCode = e.errorCode
                                if (errorCode == "ERROR_USER_NOT_FOUND") {
                                    errorTextView.text = "No matching account for the email provided!"
                                } else if (errorCode == "ERROR_USER_DISABLED") {
                                    errorTextView.text = "User account disabled!"
                                } else {
                                    errorTextView.text = e.localizedMessage
                                }
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                errorTextView.text ="The email/password is incorrect"
                            }
                            is FirebaseNetworkException -> {
                                errorTextView.text ="Error connecting!!Ckeck your internet connection"
                            }

                        }
                    }

                }
            }
        }else{errorTextView.text ="Email/Password cannot be empty1"}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginScreenBinding=null
    }
}