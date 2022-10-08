package com.example.attendx

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
     var  currentUser: FirebaseUser? = Firebase.auth.currentUser

}