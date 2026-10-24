package com.example.moviebooking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class BookingService {
 private final BookingRepository bookings; private final BookedSeatRepository seats; private final MovieRepository movies;
 public BookingService(BookingRepository bookings,BookedSeatRepository seats,MovieRepository movies){this.bookings=bookings;this.seats=seats;this.movies=movies;}
 @Transactional
 public Booking book(BookingRequest r){
  if(r==null||r.customerName()==null||r.customerName().isBlank()||r.movieName()==null||r.showDate()==null||r.showTime()==null||r.tickets()==null||r.selectedSeats()==null) throw new IllegalArgumentException("Please fill in all details.");
  if(r.showDate().isBefore(LocalDate.now())) throw new IllegalArgumentException("Choose today or a future date.");
  if(!List.of("10:00 AM","1:00 PM","6:00 PM","9:00 PM").contains(r.showTime())) throw new IllegalArgumentException("Invalid show time.");
  if(r.tickets()<1||r.tickets()>8) throw new IllegalArgumentException("Choose between 1 and 8 tickets.");
  Movie movie=movies.findByTitle(r.movieName()).orElseThrow(()->new IllegalArgumentException("Invalid movie."));
  List<String> chosen=Arrays.stream(r.selectedSeats().toUpperCase(Locale.ROOT).split(",")).map(String::trim).filter(s->!s.isEmpty()).toList();
  if(chosen.size()!=r.tickets()||new HashSet<>(chosen).size()!=chosen.size()) throw new IllegalArgumentException("Select exactly "+r.tickets()+" different seats.");
  for(String seat:chosen) if(!seat.matches("[A-E][1-8]")) throw new IllegalArgumentException("Invalid seat: "+seat);
  Set<String> already=seats.findByMovieNameAndShowDateAndShowTime(r.movieName(),r.showDate(),r.showTime()).stream().map(BookedSeat::getSeatNumber).collect(Collectors.toSet());
  for(String seat:chosen) if(already.contains(seat)) throw new SeatUnavailableException("Seat "+seat+" is already booked. Choose another seat.");
  // Database UNIQUE constraint prevents two simultaneous requests from booking the same seat.
  for(String seat:chosen) seats.save(new BookedSeat(r.movieName(),r.showDate(),r.showTime(),seat));
  seats.flush();
  return bookings.save(new Booking(r.customerName().trim(),r.movieName(),r.showDate(),r.showTime(),chosen.size(),String.join(",",chosen),BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.valueOf(chosen.size()))));
 }
 public static class SeatUnavailableException extends RuntimeException{public SeatUnavailableException(String message){super(message);}}
}
