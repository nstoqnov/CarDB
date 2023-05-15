package com.example.CarDB.Controllers;

import com.example.CarDB.Models.Trip;
import com.example.CarDB.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarDBController {

    @Autowired
    private TripService tripServiceObj;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/trips")
    public String trips(){
        return "trips";
    }

    @GetMapping("/addtrip")
    public String addTrip(){
        return "addtrip";
    }

    @PostMapping("/save")
    public String addTrip(@ModelAttribute Trip t){
        tripServiceObj.save(t);
        return "home"; }
}
