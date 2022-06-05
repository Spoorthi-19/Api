package com.example.api.controller
import com.example.api.model.Customer
import com.example.api.controller.request.BookingRequest
import com.example.api.model.Booking
import com.example.api.service.BookingService
import org.junit.jupiter.api.Test
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [BookingController::class])
@ContextConfiguration
class BookingControllerIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var bookingService: BookingService

    @Test
    internal fun `Should create booking`(){
        every { bookingService.addBooking(1, fakeBookingRequest) }

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/customer/${fakeCustomer.customerId}/booking")
                .content(objectMapper.writeValueAsBytes(fakeBookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(
                MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(fakeBookingRequest.copy(id = 1)))
            )
    }

    @Test
    internal fun `Should get the booking of a customer`() {
        every { bookingService.getBooking(1,1) } returns fakeBooking

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/customer/${fakeCustomer.customerId}/booking/${fakeBooking.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(fakeBookingRequest)))
    }

    @Test
    internal fun `Should delete booking`() {
        every { bookingService.deleteBooking(1,1) }

        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/customer/${fakeCustomer.customerId}/booking/${fakeBooking.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    fun `Should update a booking`(){
        every { bookingService.updateBooking(1,1,fakeBookingRequest.copy(Name = "Jess")) }

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/customer/${fakeCustomer.customerId}/booking/${fakeBooking.id}")
                .content(objectMapper.writeValueAsBytes(fakeBookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent)

        verify { bookingService.updateBooking(1,1,fakeBookingRequest) }
    }


}


private val fakeCustomer= Customer(customerId = 1, name = "Jack", phno = "99999", city = "Bangalore")
private val fakeBooking=Booking(id = 1, Name = "John", date = "2022-05-18", room = "103", customer = fakeCustomer)
private val fakeBookingRequest=BookingRequest(id = 1, Name = "John", date = "2022-05-18", room = "103")
