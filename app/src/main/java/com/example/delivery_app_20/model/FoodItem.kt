package com.example.delivery_app_20.model

data class FoodItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val restaurantId: String
)