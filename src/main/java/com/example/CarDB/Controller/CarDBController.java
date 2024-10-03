package com.example.CarDB.Controller;


import com.example.CarDB.Model.Trip;
import com.example.CarDB.Service.TripRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CarDBController {
    private final TripRepo tripRepo;

    public CarDBController(TripRepo tripRepo){
        this.tripRepo = tripRepo;
    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> trips(Pageable pageable){
        Page<Trip> page = tripRepo.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSortOr(Sort.by(Sort.Direction.ASC, "date"))));
        return ResponseEntity.ok(page.getContent());
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

    @PostMapping("/trips")
    private ResponseEntity<Void> createTrip(@RequestBody Trip newTripRequest, UriComponentsBuilder ucb) {
        Trip savedTrip = tripRepo.save(newTripRequest);
        URI locationOfTrip = ucb
                .path("trips/{id}")
                .buildAndExpand(savedTrip.id())
                .toUri();
        return ResponseEntity.created(locationOfTrip).build();
    }
    @PutMapping("/editTrip/{id}")
    public ResponseEntity editTrip(@PathVariable("id") Long id,@RequestBody Trip requestedTrip) {
        Optional<Trip> currentTrip = tripRepo.findById(id);
        if(currentTrip.isPresent()){
            Trip updatedTrip = new Trip(id, requestedTrip.name(), requestedTrip.trip_from(), requestedTrip.trip_to(), requestedTrip.distance(), requestedTrip.date(), requestedTrip.owner());
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
