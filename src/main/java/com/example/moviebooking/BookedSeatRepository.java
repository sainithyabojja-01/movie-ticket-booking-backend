package com.example.moviebooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface BookedSeatRepository extends JpaRepository<BookedSeat,Long>{
 List<BookedSeat> findByMovieNameAndShowDateAndShowTime(String movieName,LocalDate showDate,String showTime);
}
