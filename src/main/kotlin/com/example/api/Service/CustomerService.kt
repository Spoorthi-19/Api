package com.example.api.Service
import java.util.*
import com.example.api.Model.Customer
import com.example.api.Repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
public class CustomerService (private val customerRepository: CustomerRepository){
    fun addDetails(customer: Customer){
        customerRepository.save(customer)
    }

    fun get(): MutableIterable<Customer>? {
        return customerRepository.findAll()
    }

    fun getCustomer(customerId: Int): Optional<Customer> {
        return customerRepository.findById(customerId)
    }

    fun updateCustomer(customerId: Int, customer: Customer) {
        customer.setId(customerId)
        customerRepository.save(customer)
    }

    fun deleteCustomer(customerId: Int){
        customerRepository.deleteById(customerId)
    }




}