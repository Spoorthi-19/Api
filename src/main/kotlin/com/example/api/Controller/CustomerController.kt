package com.example.api.Controller

import com.example.api.Model.Customer
import com.example.api.Service.CustomerService
import org.springframework.web.bind.annotation.*


@RestController
public class CustomerController(private var CService: CustomerService) {

    @PostMapping("/add")
    fun addNewCustomer(@RequestParam name: String, @RequestParam phno: String, @RequestParam city: String): String{
        CService.add(name,phno,city)
        return "Saved"
    }

    @GetMapping("/list")
    fun getCustomers() : MutableIterable<Customer>? {
        return CService.get()

    }

    @DeleteMapping("/delete")
    fun deleteCustomer(@RequestParam id:Int){
        CService.delete(id)
    }
}