package com.example.api.Repository

import com.example.api.Model.Booking
//import com.example.api.Model.BookingRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
 interface BookingRepository: CrudRepository<Booking, Int> {

    fun deleteById(customerId: Int, id: Int):Optional<Booking>
    fun findByCustomerCustomerIdAndId(customerId: Int, id: Int):Optional<Booking>

}

/*@Repository
public interface BookingRequestRepository: CrudRepository< BookingRequest, Int>{
    fun deleteByCustomerCustomerIdId(customerId: Int, id: Int):Optional<Booking>
}
*/

//deleteByCustomerCustomerIdId
//findByCustomerCustomerIdId