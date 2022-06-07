package com.example.api.service
import com.example.api.exception.EntityNotFoundException
//import java.util.*
import com.example.api.model.Customer
import com.example.api.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
 class CustomerService (private val customerRepository: CustomerRepository){
    fun addDetails(customer: Customer){
        customerRepository.save(customer)
    }

    fun get(): List<Customer> {
        return customerRepository.findAll().toList()
    }

    fun getCustomer(customerId: Int): Customer {
        return customerRepository.findById(customerId)
            .orElseThrow{ EntityNotFoundException("Cannot find customer.")}
    }

    fun updateCustomer(customerId: Int, customer: Customer) {
        val existingCustomer = customerRepository.findById(customerId)
        if(existingCustomer.isEmpty){
            throw EntityNotFoundException("customer not found.")
        }
        customer.customerId = customerId
        customerRepository.save(customer)
    }

    fun deleteCustomer(customerId: Int, customer: Customer){
        val customer = customerRepository.findById(customerId)
        if(customer.isEmpty) {
            throw EntityNotFoundException("customer not found.")
        }
        customerRepository.deleteById(customerId)
    }




}