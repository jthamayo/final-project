package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

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
//
//    void testGetAddressById() {
//
//    }
//
//    void testUpdateAddress() {
//
//    }
//
//    void testDeleteAddress() {
//
//    }
//
//    void testGetAllAddresses() {
//
//    }
}
