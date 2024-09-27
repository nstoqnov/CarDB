package com.example.CarDB.Models;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table("trip")
public class Trip {
    @Id
    private long id;
    private String name;
    private String trip_from;
    private String trip_to;
    private double distance;

    private LocalDateTime date;
    private String owner;

    public Trip(String name, String trip_from, String trip_to, double distance, LocalDateTime date, String owner) {
        super();
        this.name = name;
        this.trip_from = trip_from;
        this.trip_to = trip_to;
        this.distance = distance;
        this.date = date;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
