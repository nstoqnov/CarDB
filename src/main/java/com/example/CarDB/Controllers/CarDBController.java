package com.example.CarDB.Controllers;

import com.example.CarDB.Models.Trip;
import com.example.CarDB.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarDBController {

    @Autowired
    private TripService tripServiceObj;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/trips")
    public ModelAndView trips(){
        List<Trip> list = tripServiceObj.getAllTrips();
        return new ModelAndView("trips","trip", list);
    }

    @GetMapping("/addtrip")
    public String addTrip(){
        return "addtrip";
    }

    @PostMapping("/save")
    public ModelAndView addTrip(@ModelAttribute Trip t){
        tripServiceObj.save(t);
        List<Trip> list = tripServiceObj.getAllTrips();
        return new ModelAndView("trips","trip",list); }

    @RequestMapping("/editTrip/{id}")
    public String editTrip(@PathVariable("id") int id,Model model) {
        Trip b=tripServiceObj.getTripById(id);
        model.addAttribute("trip",b);
        return "tripEdit";
    }
    @RequestMapping("/deleteTrip/{id}")
    public String deleteTrip(@PathVariable("id")int id) {
        tripServiceObj.deleteById(id);
        return "redirect:/trips";
    }
}
