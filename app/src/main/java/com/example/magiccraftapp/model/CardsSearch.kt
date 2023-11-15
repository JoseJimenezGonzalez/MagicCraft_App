package com.example.magiccraftapp.model

data class CardsSearch(
    val has_more: Boolean,
    val next_page: String,
    val data: List<Cards>
)
