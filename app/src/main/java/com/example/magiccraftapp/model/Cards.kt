package com.example.magiccraftapp.model

data class Cards(
    val name: String,
    val image_uris: ImageUris,
    val type_line: String,
    val oracle_text: String,
    val reserved: Boolean,
    val prices: Price,
    val set_name: String,
    val rarity: String,
    val mana_cost: String
)
