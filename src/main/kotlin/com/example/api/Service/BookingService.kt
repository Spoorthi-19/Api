package com.example.api.Service
import com.example.api.*
import com.example.api.Model.Booking
import com.example.api.Model.BookingRequest
import com.example.api.Repository.BookingRepository
import com.example.api.Repository.BookingRequestRepository
import com.example.api.Repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.*

@Service
public class BookingService(private val bookingRepository: BookingRepository,
                                private val bookingRepository: BookingRepository,
                                private val bookingRequestRepository: BookingRequestRepository) {

    public fun addBooking(customerId: Int, book: BookingRequest) {
        var x = CustomerRepository.findById(customerId)
        bookingRequestRepository.save(book)
        if (x.isPresent) {
            var a = x.get()
            bookingRepository.save(
                Booking(
                    id = book.id,
                    Name = book.Name,
                    date = book.date,
                    room = book.room,

                )
            )

        } else {
            throw EntityNotFoundException("customer not found")
        }

    }
   /* public fun getBooking(customerId: Int): List<Booking>{
        return bookingRepository.findAllByCustomerCustomerId(customerId)
    }*/

    public fun getBooking(customerId: Int,id: Int): Optional<Booking> {
        return bookingRepository.findByCustomerCustomerIdId(customerId,id)
    }


            public fun deleteBooking(customerId: Int,id: Int, account: BookingRequest){
                return bookingRepository.deleteByCustomerCustomerIdId(customerId,id)
        }
    }

}