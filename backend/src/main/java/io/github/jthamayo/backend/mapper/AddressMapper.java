package io.github.jthamayo.backend.mapper;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.entity.Address;

public class AddressMapper {

    public static AddressDto mapToAddressDto(Address address) {
	return new AddressDto(address.getId(), address.getCity(), address.getStreet(), address.getZip(),
		address.getCountry(), address.getNumber(), address.getType());
    }

    public static Address mapToAddress(AddressDto addressDto) {
	return new Address(addressDto.getId(), addressDto.getCity(), addressDto.getStreet(), addressDto.getZip(),
		addressDto.getCountry(), addressDto.getNumber(), addressDto.getType());
    }
}
