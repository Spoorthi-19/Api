package com.example.api.Repository

import com.example.api.Model.Booking
import com.example.api.Model.BookingRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
public interface BookingRepository: CrudRepository<Booking, Int> {

    fun findAllByEmployeeEmployeeId(customerId: Int):List<Booking>
    fun findByEmployeeEmployeeIdAndId(customerId: Int, id: Int):Optional<Booking>

}

@Repository
public interface BookingRequestRepository: CrudRepository< BookingRequest, Int>{

}