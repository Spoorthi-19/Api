package com.example.api.Model

import javax.persistence.*


@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var customerId: Int,
    private val name: String,
    private val phno: String,
    private val city: String,
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