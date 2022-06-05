package com.example.api.model

import javax.persistence.*


@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var customerId: Int,
    val name: String,
    val phno: String,
    val city: String,
    @OneToMany(cascade = [CascadeType.REMOVE],mappedBy="customer", fetch = FetchType.LAZY)
    val booking: List<Booking> =emptyList()
)
{
    /*fun getId(): Int {
        return customerId
    }
    fun setId(customerId: Int) {
        this.customerId = customerId
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
    }*/
}