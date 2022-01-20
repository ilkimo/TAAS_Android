package com.example.datahubapp.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String
) {
    override fun toString() : String = "User{${id}, ${name}, ${surname}, ${email}, ${password}}"
}