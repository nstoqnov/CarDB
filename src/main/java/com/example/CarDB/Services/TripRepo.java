package com.example.CarDB.Services;

import com.example.CarDB.Models.Trip;

import org.springframework.data.repository.CrudRepository;


public interface TripRepo extends CrudRepository<Trip, Long> {
}
