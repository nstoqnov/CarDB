package com.example.CarDB.Controllers;

import com.example.CarDB.Models.Trip;
import com.example.CarDB.Services.TripRepo;
import com.example.CarDB.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CarDBController {
    private TripRepo tripRepo;

    @GetMapping("/trips")
    public ResponseEntity<Iterable<Trip>> trips(){
        Iterable<Trip> list = tripRepo.findAll();
        ResponseEntity<Iterable<Trip>> tripsResponse = ResponseEntity.ok(list);
        return  tripsResponse;
    }
    @GetMapping("/trips/{entityId}")
    public ResponseEntity<Trip> getTripById(@PathVariable long entityId){
        Optional<Trip> foundTrip = tripRepo.findById(entityId);
        if(foundTrip.isPresent()){
            return ResponseEntity.ok(foundTrip.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/addtrip")
//    public String addTrip(){
//        return "addtrip";
//    }

//    @PostMapping("/save")
//    public ModelAndView addTrip(@ModelAttribute Trip t){
//        tripServiceObj.save(t);
//        List<Trip> list = tripServiceObj.getAllTrips();
//        return new ModelAndView("trips","trip",list); }
//
    @PutMapping("/editTrip/{id}")
    public ResponseEntity editTrip(@PathVariable("id") Long id,@RequestBody Trip requestedTrip) {
        Optional<Trip> currentTrip = tripRepo.findById(id);
        if(currentTrip.isPresent()){
            Trip updatedTrip = new Trip();
            updatedTrip.setId(currentTrip.get().getId());
            updatedTrip.setName(requestedTrip.getName());
            updatedTrip.setDate(requestedTrip.getDate());
            updatedTrip.setDistance(requestedTrip.getDistance());
            updatedTrip.setTrip_from(requestedTrip.getTrip_from());
            updatedTrip.setTrip_to(requestedTrip.getTrip_to());
            tripRepo.save(updatedTrip);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping("/deleteTrip/{id}")
    public ResponseEntity deleteTrip(@PathVariable("id")Long id) {
        tripRepo.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
