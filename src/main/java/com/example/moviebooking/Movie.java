package com.example.moviebooking;
import jakarta.persistence.*;
@Entity
public class Movie {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String title;
 private String genre; private String language; private int duration; private int price;
 protected Movie(){}
 public Movie(String title,String genre,String language,int duration,int price){this.title=title;this.genre=genre;this.language=language;this.duration=duration;this.price=price;}
 public Long getId(){return id;} public String getTitle(){return title;} public String getGenre(){return genre;}
 public String getLanguage(){return language;} public int getDuration(){return duration;} public int getPrice(){return price;}
}
