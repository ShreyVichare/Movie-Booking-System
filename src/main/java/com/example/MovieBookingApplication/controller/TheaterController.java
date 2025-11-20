package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.TheaterDto;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add-theater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDto theaterDto) {

        return ResponseEntity.ok(theaterService.addTheater(theaterDto));
    }

    @GetMapping("/get-theater-by-location")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location) {

        return ResponseEntity.ok(theaterService.getTheatersByLocation(location));
    }

    @PutMapping("/update-theater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long Id, @RequestBody TheaterDto theaterDto) {

        return ResponseEntity.ok(theaterService.updateTheater(Id, theaterDto));
    }

    @DeleteMapping("/delete-theater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {

        theaterService.deleteTheater(id);
        return ResponseEntity.ok().build();
    }
}
