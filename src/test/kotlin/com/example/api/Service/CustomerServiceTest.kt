package com.example.api.Service

import com.example.api.Exception.EntityNotFoundException
import com.example.api.Model.Customer
import com.example.api.Repository.CustomerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

internal class CustomerServiceTest{
    private val customerRepository= mockk<CustomerRepository>()
    private val customerService=CustomerService(customerRepository)

    @Test
    fun `Should create customer`(){
        every { customerRepository.save(fakeCustomer)} returns fakeCustomer.copy(customerId=1)

        customerService.addDetails(fakeCustomer)

        verify { customerRepository.save(fakeCustomer)}
    }

    @Test
    fun `Should get all customers`(){
        every { customerRepository.findAll() } returns listOf(fakeCustomer)

        val fetchedCustomers = customerService.get()

        fetchedCustomers `should be equal to` listOf(fakeCustomer)
    }

    @Test
    fun `Should get the customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.of(fakeCustomer)

        val fetchedCustomer = customerService.getCustomer(customerId = fakeCustomer.customerId)

        fetchedCustomer `should be equal to` fakeCustomer
    }

    @Test
    fun `Should throw an exception for non-existent customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.empty()

        invoking { customerService.getCustomer(fakeCustomer.customerId) } shouldThrow EntityNotFoundException("Cannot find customer.")
    }

    @Test
    fun `Should update the customer`(){
        every { customerRepository.findById(1) } returns Optional.of(fakeCustomer.copy(customerId = 1))
        every { customerRepository.save(fakeCustomer.copy(customerId = 1, phno = "855321"))} returns fakeCustomer.copy(customerId = 1, phno = "855321")

        customerService.updateCustomer(1,fakeCustomer.copy(phno = "855321"))

        verify { customerRepository.save(fakeCustomer.copy(customerId = 1,phno="855321")) }
    }

    @Test
    fun `Should throw an exception for updating non-existent customer`(){
        every{ customerRepository.findById(10) } returns Optional.empty()

        invoking { customerService.updateCustomer(10, fakeCustomer.copy(phno ="855322" )) } shouldThrow EntityNotFoundException("customer not found.")
    }

    @Test
    fun `Should delete customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.of(fakeCustomer)
        every { customerRepository.deleteById(fakeCustomer.customerId) } returns Unit

        customerService.deleteCustomer(fakeCustomer.customerId)

        verify { customerRepository.deleteById(fakeCustomer.customerId) }

    }

    @Test
    fun `Should throw an exception for deleting non-existent customer`(){
        every { customerRepository.findById(fakeCustomer.customerId) } returns Optional.empty()

        invoking { customerService.deleteCustomer(fakeCustomer.customerId) } shouldThrow EntityNotFoundException("customer not found.")

    }

}
private val fakeCustomer=Customer(customerId = 0, name = "Jack", phno = "99999", city = "Bangalore")