package com.example.api.controller

import com.example.api.model.Customer
import com.example.api.service.CustomerService
import org.junit.jupiter.api.Test
import com.fasterxml.jackson.databind.ObjectMapper
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
@WebMvcTest(controllers = [CustomerController::class])
@ContextConfiguration
class CustomerControllerIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var customerService: CustomerService

    @Test
    internal fun `Should create customer`() {
        every { customerService.addDetails(fakeCustomer) } returns Unit

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/customer")
                .content(objectMapper.writeValueAsBytes(fakeCustomer))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(
                MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(fakeCustomer.copy(customerId = 1)))
            )
    }

    @Test
    internal fun `Should get the customer`(){
        every { customerService.getCustomer(fakeCustomer.customerId) } returns fakeCustomer

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/customer/${fakeCustomer.customerId}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(fakeCustomer)))
    }

    @Test
    internal fun `Should get all customers`(){
        every { customerService.get() } returns listOf(fakeCustomer)

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/customer")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(listOf(fakeCustomer))))
    }

    @Test
    internal fun `Should update the customer`(){
        every { customerService.updateCustomer(1, fakeCustomer.copy(city = "Delhi"))} returns Unit

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/customer/${fakeCustomer.customerId}")
                .content(objectMapper.writeValueAsBytes(fakeCustomer))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent)

       verify { customerService.updateCustomer(1,fakeCustomer.copy(city = "Delhi")) }
    }

    @Test
    internal fun `Should delete customer`(){
        every { customerService.deleteCustomer(1) } returns Unit

        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/customer/${fakeCustomer.customerId}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)

    }
}
private val fakeCustomer: Customer =Customer(customerId = 1, name = "Jack", phno = "99999", city = "Bangalore")