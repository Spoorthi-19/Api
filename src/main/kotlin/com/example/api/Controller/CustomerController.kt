package com.example.api.Controller

import com.example.api.*
import com.example.api.Model.Booking
import com.example.api.Model.BookingRequest
import com.example.api.Model.Customer
import org.springframework.web.bind.annotation.*
import org.springframework.http.*
import java.util.*

@RestController
@RequestMapping("/customer")
public class CustomerController(private var customerService: CustomerService, private var bookingService: BookingService) {

    @PostMapping
    fun addNewCustomer(@RequestParam name: String, @RequestParam phno: String, @RequestParam city: String): String{
        customerService.addDetails(customer)
        return ResponseEntity.ok(customer)
    }

    @GetMapping
    fun get() : String {
        return customerService.get().toString()

    }

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: Int): Optional<Customer> {
        return customerService.get(customerId)
    }

    @PutMapping("/{customerId}")
    fun updateCustomer(@PathVariable customerId: Int, @RequestBody customer: Customer): ResponseEntity<Customer> {
        customerService.updateEmployee(customerId,customer)
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
    fun getBooking(@PathVariable customerId: Int, @PathVariable id: Int): Optional<Booking> {
        return bookingService.getBooking(customerId,id)
    }

    @DeleteMapping("/{customerId}/booking/{id}")
    fun deleteBooking(@PathVariable customerId:Int, @PathVariable id: Int): Optional<Booking>{
        bookingService.deleteBooking(customerId,id)
    }

}