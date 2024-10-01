package com.example.CarDB.Service;

import com.example.CarDB.Model.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TripRepo extends CrudRepository<Trip, Long>, PagingAndSortingRepository<Trip, Long> {
}