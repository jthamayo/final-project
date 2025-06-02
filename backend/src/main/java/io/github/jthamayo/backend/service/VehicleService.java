package io.github.jthamayo.backend.service;

import io.github.jthamayo.backend.dto.VehicleDto;

public interface VehicleService {

    VehicleDto createVehicle(VehicleDto vehicle);

    VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicle);

    VehicleDto getVehicleById(Long vehicleId);

    void deleteVehicle(Long vehicleId);
}
