package br.com.listme.data.model

import com.google.firebase.auth.FirebaseUser

data class ProfileState(
    val user: FirebaseUser? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)