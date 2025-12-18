package com.example.delivery_app_20.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String = ""
) {
    companion object {
        // Usuario vacío por defecto (no logueado)
        fun getEmptyUser(): User {
            return User(
                id = "",
                name = "",
                email = "",
                phone = "",
                address = ""
            )
        }
    }
}