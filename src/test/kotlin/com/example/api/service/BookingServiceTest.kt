package com.example.api.service


import com.example.api.controller.request.BookingRequest
import com.example.api.exception.EntityNotFoundException
import com.example.api.model.Booking
import com.example.api.model.Customer
import com.example.api.repository.BookingRepository
import com.example.api.repository.CustomerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.*

internal class BookingServiceTest{
    private val customerRepository=mockk<CustomerRepository>()
    private val bookingRepository=mockk<BookingRepository>()
    private val bookingService=BookingService(customerRepository,bookingRepository)

    @Test
    fun `Should create booking`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.of(fakeCustomer)
        every { bookingRepository.save(fakeBooking) } returns fakeBooking
        bookingService.addBooking(fakeCustomer.customerId, fakeBookingRequest)

        verify { bookingRepository.save(fakeBooking) }
    }

    @Test
    fun `Should throw an exception to create booking for non-existent customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.empty()

        invoking { bookingService.addBooking(fakeCustomer.customerId, fakeBookingRequest) } shouldThrow EntityNotFoundException("customer not found.")

    }

    @Test
    fun `Should get the booking of a customer`(){
        every { customerRepository.findById(1) } returns Optional.of(fakeCustomer.copy(customerId = 1))
        every { bookingRepository.findByCustomerCustomerIdAndId(1, fakeBooking.id) } returns Optional.of(fakeBooking)

        val fetchedBooking = bookingService.getBooking(1, fakeBooking.id)

        fetchedBooking `should be equal to` fakeBooking
    }

    @Test
    fun `Should throw an exception for getting the booking of non-existent customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.empty()

        invoking { bookingService.getBooking(fakeCustomer.customerId, fakeBooking.id) }  shouldThrow EntityNotFoundException("customer not found.")
    }

    @Test
    fun `Should delete a booking`(){
        every { customerRepository.findById(fakeCustomer.customerId) }returns Optional.of(fakeCustomer)
        every {customerRepository.existsById(fakeCustomer.customerId)} returns true
        every {bookingRepository.deleteById(fakeBooking.id)}returns Unit

        bookingService.deleteBooking(fakeCustomer.customerId, fakeBooking.id)

        verify { bookingRepository.deleteById(fakeBooking.id) }
    }

    @Test
    fun `Should throw an exception for deleting the booking of non-existent customer`(){
        //every {customerRepository.existsById(fakeCustomer.customerId)} returns false
        every { customerRepository.findById(fakeCustomer.customerId) }returns Optional.empty()

        invoking { bookingService.deleteBooking(fakeCustomer.customerId, fakeBooking.id) }shouldThrow EntityNotFoundException("booking not found.")
    }

    @Test
    fun `Should update a booking`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.of(fakeCustomer)
        every { bookingRepository.findByCustomerCustomerIdAndId(fakeCustomer.customerId, fakeBooking.id) }returns Optional.of(fakeBooking)
        every { bookingRepository.save(fakeBooking.copy(name = "Jen")) }returns fakeBooking.copy(name = "Jen")

        bookingService.updateBooking(fakeCustomer.customerId, fakeBooking.id, fakeBookingRequest.copy(name = "Jen"))

        verify { bookingRepository.save(fakeBooking.copy(name = "Jen")) }
    }

    @Test
    fun `Should throw an exception for updating the booking of non-existent customer`(){
        every {customerRepository.findById(fakeCustomer.customerId)} returns Optional.empty()
        every { bookingRepository.findByCustomerCustomerIdAndId(fakeCustomer.customerId, fakeBooking.id) } returns Optional.of(fakeBooking)
        invoking { bookingService.updateBooking(fakeCustomer.customerId, fakeBooking.id, fakeBookingRequest)}shouldThrow EntityNotFoundException("customer not found.")
    }

}

private val fakeCustomer= Customer(customerId = 0, name = "Jack", phno = "99999", city = "Bangalore")
private val fakeBooking=Booking(id = 10, name = "John", date = "2022-05-18", room = "103", customer = fakeCustomer)
private val fakeBookingRequest=BookingRequest(id = 10, name = "John", date = "2022-05-18", room = "103")
