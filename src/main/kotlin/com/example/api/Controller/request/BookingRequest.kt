package com.example.api.Controller.request

data class BookingRequest(
    var id: Int=0,
    val name: String,
    val date: String,
    val room: String
)
