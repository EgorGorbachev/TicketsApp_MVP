package com.example.yourticketsapp.utils

import android.util.Log
import com.example.yourticketsapp.data.models.User
import com.example.yourticketsapp.ui.registration_fragment.RegistrationFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firebase {

    private val fireStore = FirebaseFirestore.getInstance()

    fun registerUser(regFr: RegistrationFragment, userId: User) {
        fireStore.collection("users")
            .document(userId.userId)
            .set(userId, SetOptions.merge())
            .addOnSuccessListener {
                regFr.onSuccessReg()
            }
            .addOnFailureListener { e ->
                Log.e("exception", "Error while registering the user $e")
            }
    }
}