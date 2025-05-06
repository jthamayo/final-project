package io.github.jthamayo.backend.controller;

import java.util.List;

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

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.service.AddressService;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
	this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
	AddressDto savedAddress = addressService.createAddress(addressDto);
	return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Long addressId) {
	AddressDto addressDto = addressService.getAddressById(addressId);
	return ResponseEntity.ok(addressDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("id") Long addressId,
	    @RequestBody AddressDto updatedAddress) {
	AddressDto addressDto = addressService.updateAddress(addressId, updatedAddress);
	return ResponseEntity.ok(addressDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long addressId) {
	addressService.deleteAddress(addressId);
	return ResponseEntity.ok("The address has been successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
	List<AddressDto> addressDto = addressService.getAllAddresses();
	return ResponseEntity.ok(addressDto);
    }
}