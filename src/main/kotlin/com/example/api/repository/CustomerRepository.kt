package com.example.api.repository


import com.example.api.model.Customer
import org.springframework.data.repository.CrudRepository

public interface CustomerRepository: CrudRepository<Customer, Int> {
}