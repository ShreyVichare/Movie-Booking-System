package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.ShowDto;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/create-show")
    @PreAuthorize("hasRole('ADMIN")
    public ResponseEntity<Show> createShow(@RequestBody ShowDto showDto) {

        return ResponseEntity.ok(showService.createShow(showDto));
    }

    @GetMapping("/get-all-shows")
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/get-shows-by-movie/{id}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowsByMovie(id));
    }

    @GetMapping("/get-shows-by-theater/{id}")
    public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowsByTheater(id));
    }

    @PutMapping("/update-show/{id}")
    @PreAuthorize("hasRole('ADMIN'))")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDto showDto) {

        return ResponseEntity.ok(showService.updateShow(id, showDto));
    }

    @DeleteMapping("/delete-show/{id}")
    @PreAuthorize("hasRole('ADMIN'))")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }
}
