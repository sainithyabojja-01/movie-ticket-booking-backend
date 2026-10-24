package com.example.moviebooking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MoviebookingApplication {
 public static void main(String[] args){SpringApplication.run(MoviebookingApplication.class,args);}
 @Bean CommandLineRunner seed(MovieRepository repo){return args->{
  if(repo.count()==0){
   repo.save(new Movie("Avengers Endgame","Action","English",181,250));
   repo.save(new Movie("RRR","Action","Telugu",187,200));
   repo.save(new Movie("Pushpa 2","Action","Telugu",200,220));
   repo.save(new Movie("Devara","Action","Telugu",178,180));
  }
 };}
}
