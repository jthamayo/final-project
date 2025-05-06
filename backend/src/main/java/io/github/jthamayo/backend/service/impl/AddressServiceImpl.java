package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.AddressMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
	this.addressRepository = addressRepository;
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
	Address address = AddressMapper.mapToAddress(addressDto);
	return AddressMapper.mapToAddressDto(addressRepository.save(address));
    }

    @Override
    public AddressDto getAddressById(Long addressId) {
	Address address = addressRepository.findById(addressId)
		.orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	return AddressMapper.mapToAddressDto(address);
    }

    @Override
    public AddressDto updateAddress(Long addressId, AddressDto addressDto) {
	Address address = addressRepository.findById(addressId)
		.orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	address.setType(addressDto.getType());
	address.setCity(addressDto.getCity());
	address.setCountry(addressDto.getCountry());
	address.setNumber(addressDto.getNumber());
	address.setStreet(addressDto.getStreet());
	address.setZip(addressDto.getZip());
	return AddressMapper.mapToAddressDto(addressRepository.save(address));
    }

    @Override
    public void deleteAddress(Long addressId) {
	addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	addressRepository.deleteById(addressId);
    }

    @Override
    public List<AddressDto> getAllAddresses() {
	List<Address> addresses = addressRepository.findAll();
	return addresses.stream().map(AddressMapper::mapToAddressDto).collect(Collectors.toList());
    }

}
