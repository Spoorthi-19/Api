package com.example.api.controller

import com.example.api.controller.request.BookingRequest
import com.example.api.model.Booking
import com.example.api.service.BookingService
import org.springframework.web.bind.annotation.*
import org.springframework.http.*


@RestController
class BookingController (private val bookingService: BookingService) {
    @PostMapping("/{customerId}/booking")
    fun addBooking(
        @PathVariable customerId: Int,
        @RequestBody bookingRequest: BookingRequest
    ): ResponseEntity<BookingRequest> {
        bookingService.addBooking(customerId, bookingRequest)
        return ResponseEntity.ok(bookingRequest)
    }

    @GetMapping("/{customerId}/booking/{id}")
    fun getBooking(@PathVariable customerId: Int, @PathVariable id: Int): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.getBooking(customerId, id))
    }

    @DeleteMapping("/{customerId}/booking/{id}")
    fun deleteBooking(
        @PathVariable customerId: Int,
        @PathVariable id: Int ){
        bookingService.deleteBooking(customerId, id)
        //return ResponseEntity.ok(bookingrequest)
    }

    @PutMapping("/{customerId}/booking/{id}")
    fun updateBooking(
        @PathVariable customerId: Int,
        @PathVariable id: Int,
        @RequestBody bookingrequest: BookingRequest
    ): ResponseEntity<BookingRequest> {
        bookingService.updateBooking(customerId, id, bookingrequest)
        return ResponseEntity.ok(bookingrequest)
    }


}