package com.example.api.Controller.request

data class BookingRequest(
    var id: Int,
    val Name: String,
    var date: String,
    var room: String
)
