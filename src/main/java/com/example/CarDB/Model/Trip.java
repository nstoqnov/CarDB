package com.example.CarDB.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
@Table("TRIPS")
public record Trip(@Id Long id, String name, String trip_from, String trip_to, Double distance, LocalDateTime date, String owner) {
}
