package com.example.api.Repository


import com.example.api.Model.Customer
import org.springframework.data.repository.CrudRepository

public interface CustomerRepository: CrudRepository<Customer, Int> {
}