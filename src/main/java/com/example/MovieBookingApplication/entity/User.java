package com.example.MovieBookingApplication.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "users")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
}
