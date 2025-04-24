package io.github.jthamayo.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.NetworkMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.NetworkService;

@Service
public class NetworkServiceImpl implements NetworkService {

    private NetworkRepository networkRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public NetworkServiceImpl(NetworkRepository networkRepository, UserRepository userRepository,
	    GroupRepository groupRepository) {
	this.networkRepository = networkRepository;
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
    }

    @Override
    public NetworkDto createNetwork(NetworkDto networkDto) {
	Network network = NetworkMapper.mapToNetwork(networkDto);
	return NetworkMapper.mapToNetworkDto(networkRepository.save(network));
    }

    @Override
    public NetworkDto getNetworkById(Long networkId) {
	Network network = networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	return NetworkMapper.mapToNetworkDto(network);
    }

    @Override
    public NetworkDto updateNetwork(Long networkId, NetworkDto networkDto) {
	Network network = networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	network.setUser1(UserMapper.mapToUser(networkDto.getUser1()));
	network.setUser2(UserMapper.mapToUser(networkDto.getUser2()));
	network.setDateStart(networkDto.getDateStart());
	return NetworkMapper.mapToNetworkDto(networkRepository.save(network));
    }

    @Override
    public void deleteNetwork(Long networkId) {
	networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	networkRepository.deleteById(networkId);
    }

    @Override
    public List<NetworkDto> getAllNetworks() {
	List<Network> networks = networkRepository.findAll();
	return networks.stream().map(NetworkMapper::mapToNetworkDto).collect(Collectors.toList());
    }

    @Override
    public NetworkDto getNetworkBetweenUsers(Long userId1, Long userId2) {
	Network network = networkRepository
		.findNetworkBetweenUsers(Math.min(userId1, userId2), Math.max(userId1, userId2))
		.orElseThrow(() -> new ResourceNotFoundException(
			"Network not found between " + userId1 + " and " + userId2));
	return NetworkMapper.mapToNetworkDto(network);
    }

    @Override
    public List<UserDto> getConnectedUsers(Long userId) {
	return null;
    }

    @Override
    public List<UserDto> getMutualConnections(Long userId1, Long userId2) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<UserDto> getExclusiveConnections(Long userId1, Long userId2) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<UserDto> getGroupMutualConnections(Long groupId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<UserDto> getGroupExclusiveConnections(Long groupId) {
	// TODO Auto-generated method stub
	return null;
    }

}
