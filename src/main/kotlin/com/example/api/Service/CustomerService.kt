package com.example.api.Service
import com.example.api.Exception.EntityNotFoundException
//import java.util.*
import com.example.api.Model.Customer
import com.example.api.Repository.CustomerRepository
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

    fun deleteCustomer(customerId: Int){
        val customer = customerRepository.findById(customerId)
        if(customer.isEmpty) {
            throw EntityNotFoundException("customer not found.")
        }
        customerRepository.deleteById(customerId)
    }




}