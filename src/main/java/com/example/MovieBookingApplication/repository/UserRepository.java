package com.example.MovieBookingApplication.repository;

import com.example.MovieBookingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
