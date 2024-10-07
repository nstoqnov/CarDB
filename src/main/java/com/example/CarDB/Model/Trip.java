package com.example.CarDB.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TRIPS")
public class Trip {
    @Id
    private Long id;
    private String name;
    private String trip_from;
    private String trip_to;
    private Double distance;
    private String owner;

    public Trip() {
    }

    public Trip(Long id, String name, String trip_from, String trip_to, Double distance, String owner) {
        this.id = id;
        this.name = name;
        this.trip_from = trip_from;
        this.trip_to = trip_to;
        this.distance = distance;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrip_from() {
        return trip_from;
    }

    public void setTrip_from(String trip_from) {
        this.trip_from = trip_from;
    }

    public String getTrip_to() {
        return trip_to;
    }

    public void setTrip_to(String trip_to) {
        this.trip_to = trip_to;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
