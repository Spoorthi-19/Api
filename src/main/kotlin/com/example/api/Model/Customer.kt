package com.example.api.Model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
public class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Int,
    private var name: String,
    private var phno: String,
    private var city: String
)
{
    fun getId(): Int {
        return id
    }
    fun setId(id: Int) {
        this.id = id
    }
    fun getName(): String {
        return name
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getPhno(): String {
        return phno
    }
    fun setPhno(phno: String) {
        this.phno = phno
    }
    fun getCity(): String {
        return city
    }
    fun setCity(city: String) {
        this.city = city
    }
}