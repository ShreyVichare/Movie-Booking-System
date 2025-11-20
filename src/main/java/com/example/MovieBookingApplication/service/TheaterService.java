package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.TheaterDto;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public Theater addTheater(TheaterDto theaterDto) {
        Theater theater = Theater.builder()
                .name(theaterDto.getName())
                .location(theaterDto.getLocation())
                .capacity(theaterDto.getCapacity())
                .screenType(theaterDto.getScreenType())
                .build();

        return theaterRepository.save(theater);
    }

    public List<Theater> getTheatersByLocation(String location) {

        Optional<List<Theater>> theatersByLocation = theaterRepository.findByLocation(location);

        if (theatersByLocation.isPresent()) {
            return theatersByLocation.get();
        } else throw new RuntimeException("Theater not found in location " + location);
    }

    public Theater updateTheater(Long id, TheaterDto theaterDto) {

        Theater existingTheater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found for ID " + id));

        existingTheater.setName(theaterDto.getName());
        existingTheater.setLocation(theaterDto.getLocation());
        existingTheater.setCapacity(theaterDto.getCapacity());
        existingTheater.setScreenType(theaterDto.getScreenType());

        return theaterRepository.save(existingTheater);
    }

    public void deleteTheater(Long id) {

        theaterRepository.deleteById(id);
    }
}
