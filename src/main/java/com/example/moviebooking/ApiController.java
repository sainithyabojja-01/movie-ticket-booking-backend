package com.example.moviebooking;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@RestController @CrossOrigin(origins={"http://localhost:5173","http://127.0.0.1:5173","http://localhost:5174","http://127.0.0.1:5174","http://localhost:5500"})
public class ApiController {
 private final MovieRepository movies; private final BookingRepository bookings; private final BookedSeatRepository seats; private final BookingService service;
 public ApiController(MovieRepository movies,BookingRepository bookings,BookedSeatRepository seats,BookingService service){this.movies=movies;this.bookings=bookings;this.seats=seats;this.service=service;}
 @GetMapping("/") public String home(){return "Welcome to Movie Ticket Booking System!";}
 @GetMapping("/api/movies") public List<Movie> movies(){return movies.findAll();}
 @GetMapping("/api/bookings") public List<Booking> bookings(){return bookings.findAll();}
 @GetMapping("/api/bookings/seats") public Map<String,Object> seats(@RequestParam String movieName,@RequestParam LocalDate showDate,@RequestParam String showTime){
  List<String> all=new ArrayList<>();for(char row='A';row<='E';row++)for(int i=1;i<=8;i++)all.add(row+String.valueOf(i));
  Set<String> booked=seats.findByMovieNameAndShowDateAndShowTime(movieName,showDate,showTime).stream().map(BookedSeat::getSeatNumber).collect(Collectors.toSet());
  return Map.of("allSeats",all,"bookedSeats",booked);
 }
 @PostMapping("/api/bookings") public ResponseEntity<?> book(@RequestBody BookingRequest request){
  try {return ResponseEntity.status(HttpStatus.CREATED).body(service.book(request));}
  catch(IllegalArgumentException ex){return ResponseEntity.badRequest().body(Map.of("error",ex.getMessage()));}
  catch(BookingService.SeatUnavailableException|DataIntegrityViolationException ex){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","One or more seats are already booked. Refresh seats and choose another."));}
 }
}
