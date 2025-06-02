package io.github.jthamayo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.service.VehicleService;

@RestController
@RequestMapping("api/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
	super();
	this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDto> createAddress(@RequestBody VehicleDto vehicleDto) {
	VehicleDto savedVehicle = vehicleService.createVehicle(vehicleDto);
	return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<VehicleDto> getAddressById(@PathVariable("id") Long vehicleId) {
	VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
	return ResponseEntity.ok(vehicleDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<VehicleDto> updateAddress(@PathVariable("id") Long vehicleId,
	    @RequestBody VehicleDto updatedVehicle) {
	VehicleDto vehicelDto = vehicleService.updateVehicle(vehicleId, updatedVehicle);
	return ResponseEntity.ok(vehicelDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long vehicleId) {
	vehicleService.deleteVehicle(vehicleId);
	return ResponseEntity.ok("The vehicle has been successfully deleted");
    }

}
