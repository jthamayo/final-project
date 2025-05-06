package io.github.jthamayo.backend.service;

import java.util.List;

import io.github.jthamayo.backend.dto.AddressDto;

public interface AddressService {

    AddressDto createAddress(AddressDto addressDto);

    AddressDto getAddressById(Long addressId);

    AddressDto updateAddress(Long addressId, AddressDto addressDto);

    void deleteAddress(Long addressId);

    List<AddressDto> getAllAddresses();

}
