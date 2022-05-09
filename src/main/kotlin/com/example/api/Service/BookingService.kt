package com.example.api.Service
import com.example.api.*
import com.example.api.Controller.request.BookingRequest
import com.example.api.Model.Booking
//import com.example.api.Model.BookingRequest
import com.example.api.Repository.BookingRepository
//import com.example.api.Repository.BookingRequestRepository
import com.example.api.Repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.*

@Service
public class BookingService(private val customerRepository: CustomerRepository,
                                private val bookingRepository: BookingRepository) {

    public fun addBooking(customerId: Int, book: BookingRequest) {
        val customer = customerRepository.findById(customerId)
        if(customer.isEmpty){
            throw EntityNotFoundException("customer not found.")
        }
            bookingRepository.save(
                Booking(
                    id = book.id,
                    Name = book.Name,
                    date = book.date,
                    room = book.room,
                    customer = customer.get()

                    )
            )

        }

    public fun getBooking(customerId: Int,id: Int): Booking {
        val customer = customerRepository.findById(customerId)
        if(customer.isEmpty){
            throw EntityNotFoundException("customer not found.")
        }
        return bookingRepository.findByCustomerCustomerIdId(customerId,id)
            .orElseThrow{ EntityNotFoundException("Booking not found.")}
    }


            public fun deleteBooking(customerId: Int,id: Int): Optional<Booking> {
                val customer= customerRepository.findById(customerId)
                if(customer.isEmpty){
                    throw EntityNotFoundException("customer not found.")
                }
               return bookingRepository.deleteByCustomerCustomerIdId(customerId,id)
        }


}