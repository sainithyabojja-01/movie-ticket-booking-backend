package com.example.moviebooking;
import jakarta.persistence.*;
import java.time.*;
import java.math.BigDecimal;
@Entity @Table(name="bookings")
public class Booking {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String customerName; private String movieName; private LocalDate showDate; private String showTime;
 private int tickets; private String selectedSeats; private BigDecimal totalAmount;
 private LocalDateTime bookingTime;
 protected Booking(){}
 public Booking(String customerName,String movieName,LocalDate showDate,String showTime,int tickets,String selectedSeats,BigDecimal totalAmount){
 this.customerName=customerName;this.movieName=movieName;this.showDate=showDate;this.showTime=showTime;this.tickets=tickets;this.selectedSeats=selectedSeats;this.totalAmount=totalAmount;this.bookingTime=LocalDateTime.now();}
 public Long getId(){return id;} public String getCustomerName(){return customerName;} public String getMovieName(){return movieName;}
 public LocalDate getShowDate(){return showDate;} public String getShowTime(){return showTime;} public int getTickets(){return tickets;}
 public String getSelectedSeats(){return selectedSeats;} public BigDecimal getTotalAmount(){return totalAmount;} public LocalDateTime getBookingTime(){return bookingTime;}
}
