package io.github.jthamayo.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.mapper.NetworkMapper;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.impl.NetworkServiceImpl;

@ExtendWith(MockitoExtension.class)
public class NetworkServiceTest {

    private User user1;
    private User user2;

    @Mock
    private NetworkRepository networkRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NetworkServiceImpl networkService;

    @Nested
    class CreateNetworkTest {

	@BeforeEach
	void setUp() {
	    user1 = new User(1L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    user2 = new User(2L, "testUser2", "lastname", "username", "email", "phoneNumber");
	    when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
	    when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
	}

	@Test
	void testCreateNetwork() {
	    NetworkDto networkDto = new NetworkDto(user1.getId(), user2.getId());
	    NetworkDto savedNetworkDto = new NetworkDto(user1.getId(), user2.getId());
	    Network network = new Network(user1, user2);
	    Network savedNetwork = new Network(user1, user2);

	    when(networkRepository.findNetworkBetweenUsers(user1.getId(), user2.getId())).thenReturn(Optional.empty());

	    try (MockedStatic<NetworkMapper> mockMapper = mockStatic(NetworkMapper.class)) {
		mockMapper.when(() -> NetworkMapper.mapToNetwork(networkDto)).thenReturn(network);
		when(networkRepository.save(network)).thenReturn(savedNetwork);
		mockMapper.when(() -> NetworkMapper.mapToNetworkDto(savedNetwork)).thenReturn(savedNetworkDto);
		NetworkDto result = networkService.createNetwork(networkDto);
		assertNotNull(result);
		assertEquals(savedNetworkDto.getUserId1(), result.getUserId1());
		assertEquals(savedNetworkDto.getUserId2(), result.getUserId2());

	    }
	}

	@Test
	void testUpdateNetwork() {
	    Long networkId = 1L;
	    NetworkDto updatedNetworkDto = new NetworkDto(1L, 2L);
	    NetworkDto savedNetworkDto = new NetworkDto();
	    Network network = new Network(user2, user1);
	    Network savedNetwork = new Network(user1, user2);

	    when(networkRepository.findById(networkId)).thenReturn(Optional.of(network));
	    when(networkRepository.save(network)).thenReturn(savedNetwork);

	    try (MockedStatic<NetworkMapper> mockMapper = mockStatic(NetworkMapper.class)) {
		mockMapper.when(() -> NetworkMapper.mapToNetworkDto(savedNetwork)).thenReturn(savedNetworkDto);

		NetworkDto result = networkService.updateNetwork(networkId, updatedNetworkDto);
		assertNotNull(result);
		assertNotEquals(result.getUserId1(), updatedNetworkDto.getUserId1());
	    }
	}
    }

    @Nested
    class UseNetworkTest {

	@BeforeEach
	void setUp() {
	    user1 = new User(1L, "testUser1", "lastname", "username", "email", "phoneNumber");
	    user2 = new User(2L, "testUser2", "lastname", "username", "email", "phoneNumber");
	}

	@Test
	void testGetNetworkById() {
	    Long networkId = 1L;
	    Network network = new Network(user2, user1);
	    NetworkDto networkDto = new NetworkDto(user2.getId(), user1.getId());

	    when(networkRepository.findById(networkId)).thenReturn(Optional.of(network));
	    try (MockedStatic<NetworkMapper> mockMapper = mockStatic(NetworkMapper.class)) {
		mockMapper.when(() -> NetworkMapper.mapToNetworkDto(network)).thenReturn(networkDto);

		NetworkDto result = networkService.getNetworkById(networkId);
		assertNotNull(result);
	    }

	}

	@Test
	void testDeleteNetwork() {
	    Long networkId = 1L;
	    Network network = new Network(user2, user1);

	    when(networkRepository.findById(networkId)).thenReturn(Optional.of(network));
	    networkService.deleteNetwork(networkId);
	    verify(networkRepository).deleteById(networkId);
	}

	@Test
	void testGetAllNetworks() {
	    when(networkRepository.findAll()).thenReturn(
		    Stream.of(new Network(user1, user2), new Network(user2, user1)).collect(Collectors.toList()));

	    try (MockedStatic<NetworkMapper> mockMapper = mockStatic(NetworkMapper.class)) {
		mockMapper.when(() -> NetworkMapper.mapToNetworkDto(any(Network.class))).thenAnswer(invocation -> {
		    Network net = invocation.getArgument(0);
		    return new NetworkDto(net.getUser1().getId(), net.getUser2().getId());
		});
		List<NetworkDto> result = networkService.getAllNetworks();
		assertNotNull(result);
		assertEquals(2, result.size());
	    }

	}

    }
}
