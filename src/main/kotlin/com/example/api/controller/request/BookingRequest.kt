package com.example.api.controller.request

data class BookingRequest(
    var id: Int,
    val name: String,
    val date: String,
    val room: String
)
