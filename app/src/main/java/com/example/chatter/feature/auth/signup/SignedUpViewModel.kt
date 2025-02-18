package com.example.chatter.feature.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignedUpViewModel @Inject constructor() : ViewModel() {
    private var _state = MutableStateFlow<SignUpState>(SignUpState.Nothing)
    val state = _state.asStateFlow()

    val auth = FirebaseAuth.getInstance()

    fun signUp(name: String, email: String, password: String) {
        _state.value = SignUpState.Loading
        //Firebase SignUp
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    user?.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                    )?.addOnCompleteListener { profileUpdateTask ->
                        if (profileUpdateTask.isSuccessful) {
                            _state.value = SignUpState.Success
                        } else {
                            _state.value = SignUpState.Error
                        }
                    }
                    _state.value = SignUpState.Success
                } else {
                    _state.value = SignUpState.Error
                    // Handle the error
                    val errorMessage = it.exception?.message ?: "Unknown error occurred"
                    // Display the error message to the user
                    Log.e("SignUpViewModel", "Error: $errorMessage")

                }
            }
    }
}

sealed class SignUpState {
    object Nothing : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    object Error : SignUpState()

}