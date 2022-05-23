package com.example.api.controller.request

data class BookingRequest(
    var id: Int,
    val Name: String,
    var date: String,
    var room: String
)
