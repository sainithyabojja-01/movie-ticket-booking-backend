package com.example.moviebooking;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="booked_seats",uniqueConstraints=@UniqueConstraint(name="uq_show_seat",columnNames={"movie_name","show_date","show_time","seat_number"}))
public class BookedSeat {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="movie_name",nullable=false) private String movieName;
 @Column(name="show_date",nullable=false) private LocalDate showDate;
 @Column(name="show_time",nullable=false) private String showTime;
 @Column(name="seat_number",nullable=false) private String seatNumber;
 protected BookedSeat(){}
 public BookedSeat(String movieName,LocalDate showDate,String showTime,String seatNumber){this.movieName=movieName;this.showDate=showDate;this.showTime=showTime;this.seatNumber=seatNumber;}
 public String getSeatNumber(){return seatNumber;}
}
