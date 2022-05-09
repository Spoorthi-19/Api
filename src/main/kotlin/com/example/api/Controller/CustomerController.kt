package com.example.api.Controller

import com.example.api.*
import com.example.api.Controller.request.BookingRequest
import com.example.api.Model.Booking
//import com.example.api.Model.BookingRequest
import com.example.api.Model.Customer
import com.example.api.Service.BookingService
import com.example.api.Service.CustomerService
import org.springframework.web.bind.annotation.*
import org.springframework.http.*
import java.util.*

@RestController
@RequestMapping("/customer")
data class CustomerController(private var customerService: CustomerService, private var bookingService: BookingService) {

    @PostMapping
    fun addNewCustomer(@RequestBody customer: Customer ): ResponseEntity<Customer>{
        customerService.addDetails(customer)
        return ResponseEntity.ok(customer)
    }

    @GetMapping
    fun get() : ResponseEntity<List<Customer>> {
        return ResponseEntity.ok(customerService.get())

    }

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: Int): Customer {
        return customerService.getCustomer(customerId)
    }

    @PutMapping("/{customerId}")
    fun updateCustomer(@PathVariable customerId: Int, @RequestBody customer: Customer): ResponseEntity<Customer> {
        customerService.updateCustomer(customerId,customer)
        return ResponseEntity.ok((customer))
    }
    @DeleteMapping("/{customerId}")
    fun deleteCustomer(@PathVariable customerId:Int){
        customerService.deleteCustomer(customerId)
    }

//booking as a sub resource
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