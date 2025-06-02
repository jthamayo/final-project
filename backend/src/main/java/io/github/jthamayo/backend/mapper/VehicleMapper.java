package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.entity.Vehicle;

public class VehicleMapper {

    public static VehicleDto mapToVehicleDto(Vehicle vehicle) {
	return new VehicleDto(vehicle.getId(), vehicle.getLicensePlate(), vehicle.getModel(), vehicle.getColor(),
		vehicle.getChassisNumber());

    }

    public static Vehicle mapToVehicle(VehicleDto vehicleDto) {
	return new Vehicle(vehicleDto.getId(), vehicleDto.getLicensePlate(), vehicleDto.getModel(),
		vehicleDto.getColor(), vehicleDto.getChassisNumber());

    }
}
