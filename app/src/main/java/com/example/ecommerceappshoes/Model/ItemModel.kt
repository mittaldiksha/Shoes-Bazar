package com.example.ecommerceappshoes.Model

import java.io.Serializable


data class ItemModel(
    var title: String="",
    var descriptor: String="",
    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var oldPrice: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Double = 0.0,
) : Serializable
