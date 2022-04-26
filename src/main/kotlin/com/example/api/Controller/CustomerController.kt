package com.example.api.Controller

import com.example.api.Model.Customer
import com.example.api.Service.CustomerService
import org.springframework.web.bind.annotation.*


@RestController
public class CustomerController(private var customerService: CustomerService) {

    @PostMapping
    fun addNewCustomer(@RequestParam name: String, @RequestParam phno: String, @RequestParam city: String): String{
        customerService.add(name,phno,city)
        return "Saved"
    }

    @GetMapping
    fun getCustomers() : String {
        return customerService.get().toString()

    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@RequestParam id:Int){
        customerService.delete(id)
    }
}