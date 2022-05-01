package com.example.api.Model
import java.util.Date
import javax.persistence.*

@Entity
public class Booking(
    @Id
    private var id: Int,
    private var Name: String,
    private var date: Date,
    private var room: String,
    @ManyToOne(cascade=[CascadeType.ALL],optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="cId", referencedColumnName = "customerId")
    private var employee: Customer


) {
    fun getId(): Int {
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

    fun setCustomer(customer: Customer)
    this.customer = customer
}
fun getCustomer(customer: Customer): Customer{
    return customer
}

}
@Entity
public class BookingRequest (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int,
    var Name: String,
    var date: Date,
    var room: String
){


}