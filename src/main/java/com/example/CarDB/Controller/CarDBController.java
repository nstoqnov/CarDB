package com.example.CarDB.Controller;


import com.example.CarDB.Model.Trip;
import com.example.CarDB.Model.User;
import com.example.CarDB.Service.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class CarDBController {
    @Autowired
    JdbcAggregateTemplate template;
    private final TripRepo tripRepo;

    public CarDBController(TripRepo tripRepo){
        this.tripRepo = tripRepo;
    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> trips(Pageable pageable){
        Page<Trip> page = tripRepo.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));
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
        Trip newTrip = new Trip(11L, newTripRequest.getName(), newTripRequest.getTrip_from(), newTripRequest.getTrip_to(), newTripRequest.getDistance(),new User(1L,"Nick"));
        Trip savedTrip =template.insert(newTrip);

        URI locationOfTrip = ucb
                .path("trips/{id}")
                .buildAndExpand(savedTrip.getId())
                .toUri();
        return ResponseEntity.created(locationOfTrip).build();
    }
    @PutMapping("/trips/{id}")
    public ResponseEntity editTrip(@PathVariable("id") Long id,@RequestBody Trip requestedTrip) {
        Optional<Trip> currentTrip = tripRepo.findById(id);
        if(currentTrip.isPresent()){
            Trip updatedTrip = new Trip(id, requestedTrip.getName(), requestedTrip.getTrip_from(), requestedTrip.getTrip_to(), requestedTrip.getDistance(), requestedTrip.getOwner());
            tripRepo.save(updatedTrip);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/trips/{id}")
    public ResponseEntity deleteTrip(@PathVariable("id")Long id) {
        tripRepo.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
