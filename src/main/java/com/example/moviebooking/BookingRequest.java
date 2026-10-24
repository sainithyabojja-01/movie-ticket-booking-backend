package com.example.moviebooking;
import java.time.LocalDate;
public record BookingRequest(String customerName,String movieName,LocalDate showDate,String showTime,Integer tickets,String selectedSeats){}
