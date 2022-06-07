package com.example.api.model
import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
 data class Booking(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int=0,
    val name: String,
    val date: String,
    val room: String,
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="cId", referencedColumnName = "customerId")
    @JsonIgnore
    val customer: Customer


) {
   // private var customer: Customer

   /* fun getId(): Int {
        return id
    }

    fun getName(): String {
        return Name
    }
    fun setName(name: String) {
        this.Name = name
    }
    fun getDate(): Date{
        return date
    }
    fun setDate(date: Date){
        this.date=date
    }
    fun getRoom(): String{
        return room
    }
    fun setRoom(room: String){
        this.room= room
    }

    fun setCustomer(customer: Customer){
        this.customer = customer
}
fun getCustomer(customer: Customer): Customer{
    return customer
}
*/
}
/*@Entity
public class BookingRequest (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int,
    var Name: String,
    var date: Date,
    var room: String
){}*/