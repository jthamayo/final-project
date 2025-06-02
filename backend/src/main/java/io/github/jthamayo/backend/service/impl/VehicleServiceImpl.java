package io.github.jthamayo.backend.service.impl;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.entity.Vehicle;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.VehicleMapper;
import io.github.jthamayo.backend.repository.VehicleRepository;
import io.github.jthamayo.backend.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
	this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
	Vehicle vehicle = VehicleMapper.mapToVehicle(vehicleDto);
	return VehicleMapper.mapToVehicleDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto) {
	Vehicle vehicle = vehicleRepository.findById(vehicleId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
	vehicle.setChassisNumber(vehicleDto.getChassisNumber());
	vehicle.setColor(vehicleDto.getColor());
	vehicle.setLicensePlate(vehicleDto.getLicensePlate());
	vehicle.setModel(vehicleDto.getModel());
	return VehicleMapper.mapToVehicleDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {
	Vehicle vehicle = vehicleRepository.findById(vehicleId)
		.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
	return VehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
	vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
	vehicleRepository.deleteById(vehicleId);
    }

}
