package com.example.api.Service

import com.example.api.Model.Customer
import com.example.api.Repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
public class CustomerService (private val CRepository: CustomerRepository){



    public fun add(name: String, phno: String, city: String){
        val n= Customer(0, "", "", "")
        n.setName(name)
        n.setPhno(phno)
        n.setPhno(city)
        CRepository.save(n)
    }

    public fun get(): MutableIterable<Customer>? {
        return CRepository.findAll()
    }

    public fun delete(id: Int) {
        CRepository.deleteById(id)
    }
}