package com.example.CarDB.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String trip_from;
    private String trip_to;
    private double distance;

    private LocalDateTime date;

    public Trip(String name, String trip_from, String trip_to, double distance, LocalDateTime date) {
        super();
        this.name = name;
        this.trip_from = trip_from;
        this.trip_to = trip_to;
        this.distance = distance;
        this.date = date;
    }

    public Trip(){
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrip_from(String trip_from) {
        this.trip_from = trip_from;
    }

    public void setTrip_to(String trip_to) {
        this.trip_to = trip_to;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTrip_from() {
        return trip_from;
    }

    public String getTrip_to() {
        return trip_to;
    }


    public double getDistance() {
        return distance;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
