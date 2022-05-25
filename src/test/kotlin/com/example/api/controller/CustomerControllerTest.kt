package com.example.api.controller

import com.example.api.model.Customer
import com.example.api.service.CustomerService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.amshove.kluent.`should be equal to`


internal class CustomerControllerTest {
    private val customerService = mockk<CustomerService>()
    private val customerController = CustomerController(customerService)

    @Test
    fun `Should create customer`() {
        every { customerService.addDetails(fakeCustomer) } returns Unit

        val createdCustomer = customerController.addNewCustomer(fakeCustomer)

        createdCustomer.body `should be equal to` fakeCustomer
    }

    @Test
    fun `Should get all customers`() {
        every { customerService.get() } returns listOf(fakeCustomer)

        val fetchedCustomers = customerController.get()

        fetchedCustomers.body `should be equal to` listOf(fakeCustomer)
    }

    @Test
    fun `Should get the customer`() {
        every { customerService.getCustomer(1) } returns fakeCustomer

        val fetchedCustomer = customerController.getCustomer(fakeCustomer.customerId)

        fetchedCustomer.body `should be equal to` fakeCustomer
    }

    @Test
    fun `Should update the customer`() {
        every { customerService.updateCustomer(1, fakeCustomer.copy(city = "Delhi")) } returns Unit


        val updatedCustomer = customerController.updateCustomer(1, fakeCustomer.copy(city = "Delhi"))

        updatedCustomer.body `should be equal to` fakeCustomer.copy(city = "Delhi")
    }

    @Test
    fun `Should delete customer`() {
        every { customerService.deleteCustomer(1) } returns Unit

        customerController.deleteCustomer(1)

        verify { customerService.deleteCustomer(1) }
    }

}


    private val fakeCustomer: Customer =Customer(customerId = 1, name = "Jack", phno = "99999", city = "Bangalore")
