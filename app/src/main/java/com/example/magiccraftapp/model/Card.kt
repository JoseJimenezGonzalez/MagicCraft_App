package com.example.magiccraftapp.model

data class Card(
    val name: String,
    val image: String,
    val rarity: String,
    val reserved: String,
    val price: String,
    val edition: String,
    val text: String,
    val type: String,
    val manaCost: String
)
