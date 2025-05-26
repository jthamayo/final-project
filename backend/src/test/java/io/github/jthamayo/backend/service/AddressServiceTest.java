package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.entity.Address;
import io.github.jthamayo.backend.entity.enums.AddressType;
import io.github.jthamayo.backend.mapper.AddressMapper;
import io.github.jthamayo.backend.mapper.JobMapper;
import io.github.jthamayo.backend.repository.AddressRepository;
import io.github.jthamayo.backend.service.impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void testCreateAddress() {
	Address address = new Address(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
	AddressDto addressDto = new AddressDto(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
	AddressDto savedAddressDto = new AddressDto(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
	Address savedAddress = new Address(1L, "city", "street", "zip", "country", 6, AddressType.HOME);
	when(addressRepository.save(address)).thenReturn(savedAddress);
	try (MockedStatic<AddressMapper> mapperMock = mockStatic(AddressMapper.class)) {
	    mapperMock.when(() -> AddressMapper.mapToAddress(addressDto)).thenReturn(address);
	    mapperMock.when(() -> AddressMapper.mapToAddressDto(savedAddress)).thenReturn(savedAddressDto);
	    AddressDto result = addressService.createAddress(addressDto);
	    assertNotNull(result);
	}

    }

    @Test
    void testGetAddressById() {
	Long addressId = 1L;
	Address address = new Address(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	AddressDto addressDto = new AddressDto(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
	try (MockedStatic<AddressMapper> mapperMock = mockStatic(AddressMapper.class)) {
	    mapperMock.when(() -> AddressMapper.mapToAddressDto(address)).thenReturn(addressDto);
	    AddressDto result = addressService.getAddressById(addressId);
	    assertNotNull(result);
	}
    }

    @Test
    void testUpdateAddress() {
	Long addressId = 1L;
	Address address = new Address(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	Address savedAddress = new Address(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	AddressDto updatedAddressDto = new AddressDto(addressId, "city", "street", "zip", "country", 6,
		AddressType.HOME);
	AddressDto savedAddressDto = new AddressDto(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
	when(addressRepository.save(address)).thenReturn(savedAddress);
	try (MockedStatic<AddressMapper> mapperMock = mockStatic(AddressMapper.class)) {
	    mapperMock.when(() -> AddressMapper.mapToAddressDto(savedAddress)).thenReturn(savedAddressDto);
	    AddressDto result = addressService.updateAddress(addressId, updatedAddressDto);
	    assertNotNull(result);
	}
    }

    @Test
    void testDeleteAddress() {
	Long addressId = 1L;
	Address address = new Address(addressId, "city", "street", "zip", "country", 6, AddressType.HOME);
	when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
	addressService.deleteAddress(addressId);
	verify(addressRepository).deleteById(addressId);

    }

    @Test
    void testGetAllAddresses() {
	when(addressRepository.findAll()).thenReturn(Stream
		.of(new Address(1L, "city", "street", "zip", "country", 6, AddressType.HOME),
			new Address(2L, "city", "street", "zip", "country", 6, AddressType.HOME))
		.collect(Collectors.toList()));
	try (MockedStatic<AddressMapper> mockMapper = mockStatic(AddressMapper.class)) {
	    mockMapper.when(() -> AddressMapper.mapToAddressDto(any(Address.class))).thenAnswer(invocation -> {
		Address add = invocation.getArgument(0);
		return new AddressDto(add.getId(), add.getCity(), add.getStreet(), add.getZip(), add.getCountry(),
			add.getNumber(), add.getType());
	    });
	}
	List<AddressDto> result = addressService.getAllAddresses();
	assertNotNull(result);
	assertEquals(2, result.size());
    }
}
