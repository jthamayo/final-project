package io.github.jthamayo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jthamayo.backend.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
