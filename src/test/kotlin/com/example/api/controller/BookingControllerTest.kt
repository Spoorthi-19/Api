package com.example.api.controller

import com.example.api.controller.request.BookingRequest
import com.example.api.model.Customer
import com.example.api.model.Booking
import com.example.api.service.BookingService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.amshove.kluent.`should be equal to`

internal class BookingControllerTest{
    private val bookingService= mockk<BookingService>()
    private val bookingController=BookingController(bookingService)

    @Test
    fun `Should create booking`(){
        every { bookingService.addBooking(1, fakeBookingRequest) }

        val createdBooking=bookingController.addBooking(1, fakeBookingRequest)

        createdBooking.body `should be equal to` fakeBookingRequest
    }

    @Test
    fun `Should get the booking of a customer`(){
        every { bookingService.getBooking(1,1) } returns fakeBooking

        val fetchedBooking=bookingController.getBooking(1,1)

        fetchedBooking.body `should be equal to` fakeBooking
    }

   @Test
    fun `Should delete a booking`(){
        every { bookingService.deleteBooking(1,1) }

        bookingController.deleteBooking(1,1)

        verify { bookingService.deleteBooking(1,1) }
    }

    @Test
    fun `Should update a booking`(){
        every { bookingService.updateBooking(1,1, fakeBookingRequest.copy(Name = "Jess")) }

        val updatedBooking=bookingController.updateBooking(1,1, fakeBookingRequest.copy(Name = "Jess"))

        updatedBooking.body `should be equal to` fakeBookingRequest.copy(Name = "Jess")

    }


}

private val fakeCustomer= Customer(customerId = 1, name = "Jack", phno = "99999", city = "Bangalore")
private val fakeBooking=Booking(id = 1, Name = "John", date = "2022-05-18", room = "103", customer = fakeCustomer)
private val fakeBookingRequest=BookingRequest(id = 1, Name = "John", date = "2022-05-18", room = "103")
