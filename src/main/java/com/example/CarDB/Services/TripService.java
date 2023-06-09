package com.example.CarDB.Services;

import com.example.CarDB.Models.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepo tRepo;
    public void save(Trip t){
        tRepo.save(t);
    }

    public List<Trip> getAllTrips(){
        return tRepo.findAll();
    }

    public void deleteById(int id) {
        tRepo.deleteById(id);
    }

    public Trip getTripById(int id){
        return tRepo.findById(id).get();
    }
}
