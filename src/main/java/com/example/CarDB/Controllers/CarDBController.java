package com.example.CarDB.Controllers;

import com.example.CarDB.Models.Trip;
import com.example.CarDB.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CarDBController {

    @Autowired
    private TripService tripServiceObj;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> trips(){
        List<Trip> list = tripServiceObj.getAllTrips();
        ResponseEntity<List<Trip>> tripsResponse = ResponseEntity.ok(list);
        return  tripsResponse;
    }
    @GetMapping("/trips/{entityId}")
    public ResponseEntity<Trip> getTripById(@PathVariable long entityId){
        Trip foundTrip = tripServiceObj.getTripById(entityId);
        ResponseEntity<Trip> response = ResponseEntity.ok(foundTrip);
        return response;
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
        Trip currentTrip = tripServiceObj.getTripById(id);
        if(currentTrip != null){
            Trip updatedTrip = new Trip();
            updatedTrip.setId(currentTrip.getId());
            updatedTrip.setName(requestedTrip.getName());
            updatedTrip.setDate(requestedTrip.getDate());
            updatedTrip.setDistance(requestedTrip.getDistance());
            updatedTrip.setTrip_from(requestedTrip.getTrip_from());
            updatedTrip.setTrip_to(requestedTrip.getTrip_to());
            tripServiceObj.save(updatedTrip);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping("/deleteTrip/{id}")
    public ResponseEntity deleteTrip(@PathVariable("id")Long id) {
        tripServiceObj.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
