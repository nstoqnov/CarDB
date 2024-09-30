package com.example.CarDB.Services;



import com.example.CarDB.Models.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TripRepo extends CrudRepository<Trip, Long>, PagingAndSortingRepository<Trip, Long> {
}
