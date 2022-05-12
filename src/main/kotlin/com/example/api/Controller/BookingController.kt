package com.example.api.Controller

import com.example.api.Controller.request.BookingRequest
import com.example.api.Model.Booking
import com.example.api.Service.BookingService
import org.springframework.web.bind.annotation.*
import org.springframework.http.*


@RestController
class BookingController (private var bookingService: BookingService){
    @PostMapping("/{customerId}/booking")
    fun addBooking(@PathVariable customerId: Int, @RequestBody bookingRequest: BookingRequest): ResponseEntity<BookingRequest>{
        bookingService.addBooking(customerId,bookingRequest)
        return ResponseEntity.ok(bookingRequest)
    }

    @GetMapping("/{customerId}/booking/{id}")
    fun getBooking(@PathVariable customerId: Int, @PathVariable id: Int): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.getBooking(customerId,id))
    }

    @DeleteMapping("/{customerId}/booking/{id}")
    fun deleteBooking(@PathVariable customerId:Int, @PathVariable id: Int,@RequestBody bookingrequest: BookingRequest): ResponseEntity<BookingRequest> {
        bookingService.deleteBooking(customerId, id)
        return ResponseEntity.ok(bookingrequest)
    }
}