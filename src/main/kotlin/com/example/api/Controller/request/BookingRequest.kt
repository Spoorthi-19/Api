package com.example.api.Controller.request

import java.util.*

data class BookingRequest(
    var id: Int,
    val Name: String,
    var date: Date,
    var room: String
)
